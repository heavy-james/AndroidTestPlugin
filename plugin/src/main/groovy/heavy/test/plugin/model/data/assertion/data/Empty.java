package heavy.test.plugin.model.data.assertion.data;

import heavy.test.plugin.model.data.factory.AssertionFactory;
import heavy.test.plugin.model.data.Assertion;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/2.
 */

public class Empty extends Assertion {

    boolean conversed;

    public Empty() {

    }

    public Empty(boolean conversed) {
        this.conversed = conversed;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.DATA_ASSERTION_EMPTY;
    }

    public boolean isConversed() {
        return conversed;
    }

    public void setConversed(boolean conversed) {
        this.conversed = conversed;
    }

    @Override
    public JSONObject getJsonObject() {
        if (conversed) {
            return super.getJsonObject().put("conversed", conversed);
        }
        return super.getJsonObject();
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        conversed = object.optBoolean("conversed");
    }
}
