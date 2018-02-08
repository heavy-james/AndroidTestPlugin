package com.ktcp.test.logic;

import com.ktcp.test.model.data.interf.IJsonable;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/18.
 */

public abstract class TestCommand implements IJsonable {

    private boolean runAsCondition;
    private TestCommandInterceptor interceptor;

    public abstract String getName();

    public void execute() {
        if (interceptor != null) {
            interceptor.onExecute();
        }
    }

    public boolean isRunAsCondition() {
        return runAsCondition;
    }

    public TestCommand setRunAsCondition(boolean runAsCondition) {
        this.runAsCondition = runAsCondition;
        return this;
    }

    public void setInterceptor(TestCommandInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        runAsCondition = object.optBoolean("run_as_condition");
    }

    @Override
    public JSONObject getJsonObject() {
        return new JSONObject().put("command_name", getName()).putOpt("run_as_condition", runAsCondition);
    }

    public interface TestCommandInterceptor {
        void onExecute();
    }

}
