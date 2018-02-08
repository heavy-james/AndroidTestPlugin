package heavy.test.plugin.model.data.testable.global;

import heavy.test.plugin.util.TextUtil;
import heavy.test.plugin.model.data.Testable;
import heavy.test.plugin.model.data.factory.TestableFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/31.
 */

public abstract class GlobalTestable extends Testable {

    public static final String GLOBAL_TESTABLE_KEY = "global_test_type";
    protected String description;

    public GlobalTestable() {

    }

    public GlobalTestable(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getTestableType() {
        return TestableFactory.TYPE_GLOBAL;
    }

    public abstract String getGlobalTestableType();

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject().put(GLOBAL_TESTABLE_KEY, getGlobalTestableType());

        if (!TextUtil.isEmpty(description)) {
            result.putOpt("description", description);
        }
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        description = object.optString("description");
    }

}
