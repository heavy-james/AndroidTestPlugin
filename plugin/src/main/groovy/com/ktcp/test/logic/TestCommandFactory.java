package com.ktcp.test.logic;

import com.ktcp.test.logic.command.FinishActivity;
import com.ktcp.test.logic.command.GetRuntimeValue;
import com.ktcp.test.logic.command.RecordResult;
import com.ktcp.test.logic.command.RunTestObject;
import com.ktcp.test.logic.command.SetUpActivity;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/19.
 */

public class TestCommandFactory {

    public static final String RUN_TEST_OBJECT = "run_test_object";
    public static final String SET_UP_ACTIVITY = "set_up_activity";
    public static final String FINISH_ACTIVITY = "finish_activity";
    public static final String RECORD_RESULT = "record_result";
    public static final String STOP_TEST = "stop_test";
    public static final String GET_RUNTIME_VALUE = "get_runtime_value";


    public static TestCommand createCommand(JSONObject jsonObject) {

        if (jsonObject != null) {
            TestCommand testCommand = null;
            String commandName = jsonObject.optString("command_name");

            do {
                if (RUN_TEST_OBJECT.equals(commandName)) {
                    testCommand = new RunTestObject(null);
                    break;
                }

                if (SET_UP_ACTIVITY.equals(commandName)) {
                    testCommand = new SetUpActivity(null);
                    break;
                }

                if (FINISH_ACTIVITY.equals(commandName)) {
                    testCommand = new FinishActivity();
                    break;
                }

                if (RECORD_RESULT.equals(commandName)) {
                    testCommand = new RecordResult();
                    break;
                }

                if (GET_RUNTIME_VALUE.equals(commandName)) {
                    testCommand = new GetRuntimeValue(null);
                    break;
                }
            } while (false);

            if (testCommand != null) {
                testCommand.parseJsonObject(jsonObject);
                return testCommand;
            }
            throw new IllegalArgumentException("unknown command name : " + commandName);
        }

        return null;
    }
}
