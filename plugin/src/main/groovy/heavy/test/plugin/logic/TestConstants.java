package heavy.test.plugin.logic;

import java.io.File;

/**
 * Created by heavy on 2017/6/6.
 */

public class TestConstants {
    public static final String ROOT_CLOSURE_NAME = "TestModel";
    public static final String RESULT_FILE_PATH = "test_result";
    public static final String RESULT_FILE_NAME = "test_result.txt";
    public static final int CONTROLLOER_PORT = 10086;
    public static final int RUNNER_PORT = 10001;

    private static String sBuildDir = null;

    public static String getBuildDir() {
        return sBuildDir;
    }

    public static void setBuildDir(String buildDir) {
        sBuildDir = buildDir;
    }

    public static String getResultFileName() {
        return getBuildDir() + File.separator + RESULT_FILE_PATH + File.separator + RESULT_FILE_NAME;
    }

}
