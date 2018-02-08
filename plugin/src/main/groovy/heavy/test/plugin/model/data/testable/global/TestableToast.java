package heavy.test.plugin.model.data.testable.global;

import heavy.test.plugin.model.data.factory.GlobalTestableFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/1.
 */

public class TestableToast extends GlobalTestable {

    long toastTime = 2000;

    public TestableToast() {

    }

    public TestableToast(String msg, long toastTime) {
        this.description = msg;
        this.toastTime = toastTime;
    }

    @Override
    public String getGlobalTestableType() {
        return GlobalTestableFactory.TYPE_TOAST;
    }

    public String getToastMessage() {
        return description;
    }

    public void setToastMessage(String msg) {
        description = msg;
    }

    public long getToastTime() {
        return toastTime;
    }

    public void setToastTime(long toastTime) {
        this.toastTime = toastTime;
    }

    @Override
    public JSONObject getJsonObject() {
        return super.getJsonObject().putOpt("toast_time", toastTime);
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        toastTime = object.optLong("toast_time", 2000);
    }
}
