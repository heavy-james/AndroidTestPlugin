package heavy.test.plugin.logic;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import groovy.lang.Closure;
import heavy.test.plugin.logic.transport.SocketClient;
import heavy.test.plugin.model.data.TestBlock;
import heavy.test.plugin.model.data.TestContext;
import heavy.test.plugin.model.data.TestObject;
import heavy.test.plugin.model.data.factory.TestObjectFactory;
import heavy.test.plugin.model.data.reflection.RuntimeValue;
import heavy.test.plugin.model.data.result.RecordResult;
import heavy.test.plugin.model.data.testable.global.ConditionedTestable;
import heavy.test.plugin.model.data.testable.global.GetRuntimeValue;
import heavy.test.plugin.model.wrapper.testable.TestableWrapper;
import heavy.test.plugin.util.LogUtil;

/**
 * Created by heavy on 17/6/27.
 */

public class TestManager {

    private static final String TAG = "TestManager";

    private static TestManager instance;
    SocketClient socketClient;
    TestResultRecorder testResult;
    Closure whenTestError;
    Gson mGson = new Gson();
    private boolean stopWhenError;

    private TestManager() {
        testResult = new TestResultRecorder(TestConstants.getResultFileName());
        initPort();
    }

    public static synchronized TestManager getInstance() {
        if (instance == null) {
            instance = new TestManager();
        }
        return instance;
    }

    private void initPort() {
        try {
            Thread.sleep(3000);
            LogUtil.d(TAG, "new socket start bind server");
            Socket socket = new Socket("127.0.0.1", 10086);
            LogUtil.d(TAG, "new socket bind server success");
            socketClient = new SocketClient(socket);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            LogUtil.d(TAG, "bind server with socket error.");
        }
    }


    public void setTestResultFile(String resultFile) {
        testResult.setResultFile(resultFile);
    }

    public void needRecordResult(boolean recordResult) {
        testResult.setNeedRecordResult(recordResult);
    }

    public void setWhenTestAbortClosure(Closure closure) {
        whenTestError = closure;
    }

    public void setStopWhenError(boolean stopWhenError) {
        this.stopWhenError = stopWhenError;
    }

    public boolean execute(TestObject testCommand) throws RuntimeException {
        if (testCommand instanceof RecordResult) {
            RecordResult testResult = (RecordResult) testCommand;
            this.testResult.record(testResult);
            if (testResult.isFailed()) {
                if (testResult.isRunAsCondition()) {
                    return false;
                } else {
                    if (whenTestError != null) {
                        whenTestError.call();
                    }

                    if (this.testResult.needRecordResult()) {
                        for (String command : TestConstants.getTestDataCollectingCommands()) {
                            LogUtil.d(TAG, "data collection executing : " + command);
                            try {
                                Process process = Runtime.getRuntime().exec(command);
                                process.waitFor();
                            } catch (IOException e) {
                                e.printStackTrace();
                                LogUtil.e(TAG, "collecting data failed cause by : " + e.getMessage());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (stopWhenError) {
                        throw new RuntimeException(testResult.getInfo());
                    }
                }
            }
        }
        return true;
    }

    public RuntimeValue resolveRuntimeValue(RuntimeValue runtimeValue) {
        sendForResult(new GetRuntimeValue(runtimeValue));
        return runtimeValue;
    }

    public boolean sendForResult(TestObject testCommand) {

        TestObject responseCommand = null;
        if (socketClient != null && testCommand != null) {

            LogUtil.d(TAG, "sendForResult testCommand : " + testCommand);

            String data = testCommand.getJsonObject().toString();

            LogUtil.d(TAG, "sendForResult testCommandData : " + data);

            socketClient.println(data);

            String resultString = socketClient.readLine();

            try {
                LogUtil.d(TAG, "received string : " + resultString);
                responseCommand = TestObjectFactory.createTestObject(new JSONObject(resultString));
            } catch (Exception e) {
                throw new IllegalStateException("received unexpected test result from server : " + resultString);
            }
        }

        if (testCommand instanceof GetRuntimeValue) {
            GetRuntimeValue testCmd = (GetRuntimeValue) testCommand;
            if (responseCommand != null) {
                GetRuntimeValue respCmd = (GetRuntimeValue) responseCommand;
                if (respCmd.getRuntimeValue() != null) {
                    testCmd.getRuntimeValue().setValue(respCmd.getRuntimeValue().getValue());
                }
            }
            return true;
        }
        return execute(responseCommand);
    }

    public boolean runTestObject(TestObject testObject) {
        boolean result = true;
        if (testObject != null) {
            for (int i = 0; i < testObject.getRepeatCount(); i++) {
                if (testObject instanceof ConditionedTestable) {
                    runConditionTestable((ConditionedTestable) testObject);
                    continue;
                }
                if (testObject instanceof TestBlock) {
                    result &= runTestObjects(1, testObject.getContentObjects());
                    continue;
                }
                result &= sendForResult(testObject);
            }
        }
        return result;
    }

    public boolean runTestObjects(int repeatCount, List<TestObject> testObjects) {
        boolean result = true;
        if (testObjects != null) {
            for (int i = 0; i < repeatCount; i++) {
                for (TestObject TestObject : testObjects) {
                    result &= TestManager.getInstance().runTestObject(TestObject);
                }
            }
        }
        return result;
    }

    public boolean checkCondition(TestObject testObject) {
        boolean result = true;
        if (testObject != null) {
            for (TestObject TestObject : testObject.getContentObjects()) {
                TestObject.setRunAsCondition(true);
                result &= TestManager.getInstance().sendForResult(TestObject);
            }
        }
        return result;
    }

    public TestObject getTestObjectFromClosure(TestContext testContext, Closure closure) {
        if (closure != null) {
            TestableWrapper testableWrapper = new TestableWrapper(testContext, new TestObject());
            closure.setDelegate(testableWrapper);
            closure.setResolveStrategy(Closure.DELEGATE_ONLY);
            closure.call();
            return testableWrapper.getTestObject();
        }
        return null;
    }


    public void runConditionTestable(ConditionedTestable conditionedTestable) {
        if (conditionedTestable == null) {
            return;
        }
        TestObject conditionObject = getTestObjectFromClosure(conditionedTestable.getTestContext(), conditionedTestable.getConditionClosure());
        TestObject preConditionObject = getTestObjectFromClosure(conditionedTestable.getTestContext(), conditionedTestable.getPreconditionClosure());
        TestObject trueCaseObject = getTestObjectFromClosure(conditionedTestable.getTestContext(), conditionedTestable.getTrueCaseClosure());
        TestObject falseCaseObject = getTestObjectFromClosure(conditionedTestable.getTestContext(), conditionedTestable.getFalseCaseClosure());

        long counter = 0;
        boolean conditionSatisfied = TestManager.getInstance().checkCondition(conditionObject);
        while (counter < conditionedTestable.getTimeOut() && !conditionSatisfied) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter += 500;
            if (preConditionObject != null) {
                runTestObjects(preConditionObject.getRepeatCount(), preConditionObject.getContentObjects());
            }
            conditionSatisfied = checkCondition(conditionObject);
        }

        conditionSatisfied &= counter <= conditionedTestable.getTimeOut();
        conditionSatisfied ^= conditionedTestable.isConversed();

        TestObject testObject = null;
        if (conditionSatisfied) {
            testObject = trueCaseObject;
        } else {
            testObject = falseCaseObject;
        }
        if (testObject != null) {
            runTestObjects(testObject.getRepeatCount(), testObject.getContentObjects());
        }
    }
}
