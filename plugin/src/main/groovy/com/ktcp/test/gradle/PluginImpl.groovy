package com.ktcp.test.gradle

import com.ktcp.test.model.wrapper.TestWrapper
import com.ktcp.test.util.LogUtil
import com.ktcp.test.util.ProcessUtil
import org.gradle.api.Plugin
import org.gradle.api.Project

import static com.ktcp.test.logic.TestConstants.ROOT_CLOSURE_NAME
import static com.ktcp.test.logic.TestConstants.setBuildDir

public class PluginImpl implements Plugin<Project> {

    static final String TAG = "AndroidTestPlugin"

    void apply(Project project) {
        LogUtil.init(getLogLevel(project), false, null)

        LogUtil.d(TAG, "apply start")
        Properties properties = new Properties()
        properties.load(project.rootProject.file('local.properties').newDataInputStream())
        String sdkPath = properties.getProperty('sdk.dir')
        String adbPath = sdkPath + "/platform-tools/adb";

        LogUtil.d(TAG, "local adb path : " + adbPath)

        setBuildDir(project.buildDir.absolutePath)

        //adb forward init
        initTransport(adbPath)

        //instrumentation start
        startTest(adbPath)

        //run config
        project.extensions.add(ROOT_CLOSURE_NAME, new TestWrapper())

        //tag task, do nothing meaningful
        project.task('startTest') {
            //start test
        }
    }

    private void initTransport(String adbPath) {
        LogUtil.d(TAG, "initTransport...")
        String forwardCommand = adbPath + " forward tcp:10086 tcp:10001"
        Process forward = forwardCommand.execute()
        ProcessUtil.readProcessOutput(forward)
        forward.waitFor()
    }

    private void startTest(String adbPath) {
        //start test
        LogUtil.d(TAG, "startTest...")
        String testCommand = adbPath + " shell am instrument -w -r -e class heavy.tool.test.activity.TestEntrance heavy.tool.test/android.support.test.runner.AndroidJUnitRunner"
        testCommand.execute()
    }

    private int getLogLevel(Project project) {

        if (project.getLogger().isDebugEnabled()) {
            return LogUtil.LEVEL_DEBUG
        }
        if (project.getLogger().isInfoEnabled()) {
            return LogUtil.LEVEL_INFO
        }
        return LogUtil.LEVEL_VERBOSE
    }

}

