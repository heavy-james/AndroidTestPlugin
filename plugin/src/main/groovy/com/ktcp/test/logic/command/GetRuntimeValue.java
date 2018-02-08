package com.ktcp.test.logic.command;

import com.ktcp.test.logic.TestCommand;
import com.ktcp.test.logic.TestCommandFactory;
import com.ktcp.test.model.data.reflection.RuntimeValue;

import org.json.JSONObject;

/**
 * Created by heavy on 17/6/29.
 */

public class GetRuntimeValue extends TestCommand {


    RuntimeValue mRuntimeValue;

    public GetRuntimeValue(RuntimeValue runtimeValue) {
        mRuntimeValue = runtimeValue;
    }

    public RuntimeValue getRuntimeValue() {
        return mRuntimeValue;
    }

    public void setRuntimeValue(RuntimeValue mRuntimeValue) {
        this.mRuntimeValue = mRuntimeValue;
    }

    @Override
    public String getName() {
        return TestCommandFactory.GET_RUNTIME_VALUE;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        mRuntimeValue = new RuntimeValue();
        mRuntimeValue.parseJsonObject(object.optJSONObject("runtime_value"));
    }

    @Override
    public JSONObject getJsonObject() {
        return super.getJsonObject().put("runtime_value", mRuntimeValue.getJsonObject());

    }
}
