package com.ktcp.test.logic.command;

import com.ktcp.test.logic.TestCommand;
import com.ktcp.test.logic.TestCommandFactory;
import com.ktcp.test.model.data.IntentData;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/20.
 */

public class SetUpActivity extends TestCommand {

    private IntentData intentData;

    public SetUpActivity(IntentData intentData) {
        this.intentData = intentData;
    }

    public IntentData getIntentData() {
        return intentData;
    }

    public void setIntentData(IntentData intentData) {
        this.intentData = intentData;
    }

    @Override
    public String getName() {
        return TestCommandFactory.SET_UP_ACTIVITY;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.put("intent_data", intentData.getJsonObject());
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        intentData = new IntentData();
        intentData.parseJsonObject(object.optJSONObject("intent_data"));
    }
}
