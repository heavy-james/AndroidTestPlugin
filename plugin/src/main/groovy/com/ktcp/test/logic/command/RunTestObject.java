package com.ktcp.test.logic.command;

import com.ktcp.test.logic.TestCommand;
import com.ktcp.test.logic.TestCommandFactory;
import com.ktcp.test.model.data.factory.TestObjectFactory;
import com.ktcp.test.model.data.interf.ITestObject;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/19.
 */

public class RunTestObject extends TestCommand {

    private ITestObject testObject;

    public RunTestObject(ITestObject testObject) {
        this.testObject = testObject;
    }

    @Override
    public String getName() {
        return TestCommandFactory.RUN_TEST_OBJECT;
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        if (testObject != null) {
            result.put("test_object", testObject.getJsonObject());
        }
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        testObject = TestObjectFactory.createTestObject(object.optJSONObject("test_object"));
    }

    public ITestObject getTestObject() {
        return testObject;
    }
}
