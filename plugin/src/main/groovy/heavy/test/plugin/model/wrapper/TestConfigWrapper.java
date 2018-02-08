package heavy.test.plugin.model.wrapper;


import groovy.lang.Closure;
import heavy.test.plugin.logic.TestConstants;
import heavy.test.plugin.logic.TestManager;
import heavy.test.plugin.util.LogUtil;

/**
 * Created by heavy on 2018/2/6.
 */

public class TestConfigWrapper {

    private static final String TAG = "TestConfigWrapper";

    private int VERBOSE = LogUtil.LEVEL_VERBOSE;

    private int DEBUG = LogUtil.LEVEL_DEBUG;

    private int INFO = LogUtil.LEVEL_INFO;

    private int WARNING = LogUtil.LEVEL_WARN;

    private int ERROR = LogUtil.LEVEL_ERROR;

    public void saveTestResult(boolean save) {
        LogUtil.d(TAG, "saveTestResult : " + save);
        TestManager.getInstance().needRecordResult(save);
    }

    public void logLevel(int logLevel) {
        LogUtil.d(TAG, "logLevel : " + logLevel);
        LogUtil.setLogLevel(logLevel);
    }

    public void testResultFile(String resultFile) {
        LogUtil.d(TAG, "testResultFile : " + resultFile);
        TestManager.getInstance().setTestResultFile(resultFile);
    }

    public void whenTestError(Closure closure) {
        LogUtil.d(TAG, "whenTestAbort : " + closure);
        TestManager.getInstance().setWhenTestAbortClosure(closure);
    }

    public void stopWhenError(boolean stopWhenError) {
        LogUtil.d(TAG, "stopWhenError : " + stopWhenError);
        TestManager.getInstance().setStopWhenError(stopWhenError);
    }

    public void buildDir(String buildDir) {
        LogUtil.d(TAG, "buildDir : " + buildDir);
        TestConstants.setBuildDir(buildDir);
    }

    public void targetAppLogPath(String... paths) {
        for (String path : paths) {
            LogUtil.d(TAG, "add targetAppLogPath : " + path);
            TestConstants.addTargetAppLogPath(path);
        }
    }

}
