package com.ktcp.test.model.data.factory;

import com.ktcp.test.model.data.testable.global.GlobalTestable;
import com.ktcp.test.model.data.testable.global.TestableDelay;
import com.ktcp.test.model.data.testable.global.TestableException;
import com.ktcp.test.model.data.testable.global.TestableMessage;
import com.ktcp.test.model.data.testable.global.TestableStop;
import com.ktcp.test.model.data.testable.global.TestableToast;
import com.ktcp.test.util.LogUtil;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/31.
 */

public class GlobalTestableFactory {

    public static final String TAG = "GlobalTestableFactory";

    public static final String TYPE_DELAY = "type_delay";
    public static final String TYPE_EXCEPTION = "type_exception";
    public static final String TYPE_TOAST = "type_toast";
    public static final String TYPE_MESSAGE = "type_message";
    public static final String TYPE_STOP = "type_stop";

    public static GlobalTestable createGlobalTestable(JSONObject object) {
        if (object == null) {
            LogUtil.e(TAG, "createTestable return null, json object is null");
            return null;
        }

        if (TYPE_DELAY.equals(object.optString(GlobalTestable.GLOBAL_TESTABLE_KEY))) {
            GlobalTestable result = new TestableDelay();
            result.parseJsonObject(object);
            return result;
        }

        if (TYPE_EXCEPTION.equals(object.optString(GlobalTestable.GLOBAL_TESTABLE_KEY))) {
            GlobalTestable result = new TestableException();
            result.parseJsonObject(object);
            return result;
        }

        if (TYPE_MESSAGE.equals(object.optString(GlobalTestable.GLOBAL_TESTABLE_KEY))) {
            GlobalTestable result = new TestableMessage();
            result.parseJsonObject(object);
            return result;
        }

        if (TYPE_TOAST.equals(object.optString(GlobalTestable.GLOBAL_TESTABLE_KEY))) {
            GlobalTestable result = new TestableToast();
            result.parseJsonObject(object);
            return result;
        }

        if (TYPE_STOP.equals(object.optString(GlobalTestable.GLOBAL_TESTABLE_KEY))) {
            GlobalTestable result = new TestableStop();
            result.parseJsonObject(object);
            return result;
        }

        throw new IllegalArgumentException("createTestable return null, unrecognised type : " + object.optString(GlobalTestable.GLOBAL_TESTABLE_KEY));
    }
}
