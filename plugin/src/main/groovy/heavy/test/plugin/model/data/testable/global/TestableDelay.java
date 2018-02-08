package heavy.test.plugin.model.data.testable.global;

import heavy.test.plugin.model.data.factory.GlobalTestableFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/1.
 */

public class TestableDelay extends GlobalTestable {

    long delayMillis;

    public TestableDelay() {

    }

    public TestableDelay(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public long getDelayMillis() {
        return delayMillis;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.putOpt("delayMillis", delayMillis);
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        delayMillis = object.optInt("delayMillis");
    }

    @Override
    public String getGlobalTestableType() {
        return GlobalTestableFactory.TYPE_DELAY;
    }


}
