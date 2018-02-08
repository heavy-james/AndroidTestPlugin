package heavy.test.plugin.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import heavy.test.plugin.util.DateTimeUtil;

/**
 * Created by heavy on 2017/6/6.
 */

public class TestConstants {

    public static final String ROOT_CLOSURE_NAME = "TestModel";

    public static final String CONFIG_CLOSURE_NAME = "TestConfig";

    public static final String RESULT_FILE_NAME = "test_result.txt";


    public static final String COMMAND_SCREEN_CAP = "adb shell screencap -p /sdcard/screenshot.png";

    public static final String COMMAND_SAVE_SCREEN_CAP = "adb pull /sdcard/screenshot.png  %s";

    public static final String COMMAND_ADB_PULL = "adb pull %s %s";


    public static final int CONTROLLOER_PORT = 10086;
    public static final int RUNNER_PORT = 10001;


    private static String mBuildDir = null;

    private static Set<String> mTargetAppLogPaths = new HashSet<>();

    public static String getBuildDir() {
        return mBuildDir;
    }

    public static void setBuildDir(String buildDir) {
        mBuildDir = buildDir + File.separator + DateTimeUtil.currentInSperator();
    }

    public static String getResultFileName() {
        return getBuildDir() + File.separator + RESULT_FILE_NAME;
    }

    public static List<String> getTestDataCollectingCommands() {
        List<String> result = new ArrayList<>();
        result.add(COMMAND_SCREEN_CAP);
        result.add(String.format(COMMAND_SAVE_SCREEN_CAP, getBuildDir()));
        result.add(String.format(COMMAND_ADB_PULL, "/sdcard/xlog/heavy.tool.test", getBuildDir()));
        for (String path : mTargetAppLogPaths) {
            result.add(String.format(COMMAND_ADB_PULL, path, getBuildDir()));
        }
        return result;
    }

    public static void addTargetAppLogPath(String path) {
        mTargetAppLogPaths.add(path);
    }
}
