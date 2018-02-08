package heavy.test.plugin.logic.command;

import heavy.test.plugin.logic.TestCommand;
import heavy.test.plugin.model.data.interf.ITestObject;
import heavy.test.plugin.logic.TestCommandFactory;
import heavy.test.plugin.model.data.factory.TestObjectFactory;

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
