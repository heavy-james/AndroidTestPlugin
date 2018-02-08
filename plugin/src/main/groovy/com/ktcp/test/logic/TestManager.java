package com.ktcp.test.logic;

import com.ktcp.test.logic.command.GetRuntimeValue;
import com.ktcp.test.logic.command.RecordResult;
import com.ktcp.test.logic.command.RunTestObject;
import com.ktcp.test.logic.transport.SocketClient;
import com.ktcp.test.model.data.TestBlock;
import com.ktcp.test.model.data.TestContext;
import com.ktcp.test.model.data.TestObject;
import com.ktcp.test.model.data.interf.ITestObject;
import com.ktcp.test.model.data.reflection.RuntimeValue;
import com.ktcp.test.model.data.testable.global.ConditionedTestable;
import com.ktcp.test.model.wrapper.testable.TestableWrapper;
import com.ktcp.test.util.LogUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import groovy.lang.Closure;

/**
 * Created by heavy on 17/6/27.
 */

public class TestManager {

    private static final String TAG = "TestManager";
    private static TestManager instance;
    SocketClient socketClient;
    TestResultRecorder testResult;

    private TestManager() {
        try {
            testResult = new TestResultRecorder(TestConstants.getResultFileName());
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

    public static synchronized TestManager getInstance() {
        if (instance == null) {
            instance = new TestManager();
        }
        return instance;
    }

    public boolean execute(TestCommand testCommand) throws RuntimeException {
        if (testCommand instanceof RecordResult) {
            RecordResult recordResult = (RecordResult) testCommand;
            if (recordResult.isFailed()) {
                if (recordResult.isRunAsCondition()) {
                    return false;
                } else {
                    throw new RuntimeException(recordResult.getInfo());
                }
            }
            testResult.record(recordResult);
        }
        if (testCommand instanceof GetRuntimeValue) {
            GetRuntimeValue getRuntimeValue = (GetRuntimeValue) testCommand;

        }
        return true;
    }

    public RuntimeValue resolveRuntimeValue(RuntimeValue runtimeValue) {
        sendForResult(new GetRuntimeValue(runtimeValue));
        return runtimeValue;
    }

    public boolean sendForResult(TestCommand testCommand) {
        TestCommand responseCommand = null;
        if (socketClient != null && testCommand != null) {
            LogUtil.d(TAG, "sendForResult testCommand : " + testCommand.getJsonObject().toString());
            socketClient.println(testCommand.getJsonObject().toString());
            String resultString = socketClient.readLine();
            if(resultString != null){
                try {
                    LogUtil.d(TAG, "received string : " + resultString);
                    responseCommand = TestCommandFactory.createCommand(new JSONObject(resultString));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                LogUtil.w(TAG, "received null string from test apk.");
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

    public boolean runTestObject(ITestObject testObject) {
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
                result &= sendForResult(new RunTestObject(testObject));
            }
        }
        return result;
    }

    public boolean runTestObjects(int repeatCount, List<ITestObject> testObjects) {
        boolean result = true;
        if (testObjects != null) {
            for (int i = 0; i < repeatCount; i++) {
                for (ITestObject iTestObject : testObjects) {
                    result &= TestManager.getInstance().runTestObject(iTestObject);
                }
            }
        }
        return result;
    }

    public boolean checkCondition(ITestObject testObject) {
        boolean result = true;
        if (testObject != null) {
            for (ITestObject iTestObject : testObject.getContentObjects()) {
                result &= TestManager.getInstance().sendForResult(new RunTestObject(iTestObject).setRunAsCondition(true));
            }
        }
        return result;
    }

    public ITestObject getTestObjectFromClosure(TestContext testContext, Closure closure) {
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
        ITestObject conditionObject = getTestObjectFromClosure(conditionedTestable.getTestContext(), conditionedTestable.getConditionClosure());
        ITestObject preConditionObject = getTestObjectFromClosure(conditionedTestable.getTestContext(), conditionedTestable.getPreconditionClosure());
        ITestObject trueCaseObject = getTestObjectFromClosure(conditionedTestable.getTestContext(), conditionedTestable.getTrueCaseClosure());
        ITestObject falseCaseObject = getTestObjectFromClosure(conditionedTestable.getTestContext(), conditionedTestable.getFalseCaseClosure());

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

        ITestObject testObject = null;
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
