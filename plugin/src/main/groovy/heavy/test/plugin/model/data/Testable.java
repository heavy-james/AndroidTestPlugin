package heavy.test.plugin.model.data;

import heavy.test.plugin.model.data.factory.TestObjectFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/31.
 */

public abstract class Testable extends TestObject {

    public static final String TESTABLE_KEY = "testable_type";

    public Testable() {
        objectType = TestObjectFactory.OBJECT_TYPE_TESTABLE;
    }

    @Override
    public String getObjectType() {
        return TestObjectFactory.OBJECT_TYPE_TESTABLE;
    }

    public abstract String getTestableType();

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.put(TESTABLE_KEY, getTestableType());
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
    }
}
