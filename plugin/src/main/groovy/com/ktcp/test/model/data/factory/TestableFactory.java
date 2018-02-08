package com.ktcp.test.model.data.factory;

import com.ktcp.test.model.data.Testable;
import com.ktcp.test.util.LogUtil;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/31.
 */

public class TestableFactory {

    public static final String TAG = "TestableFactory";

    public static final String TYPE_VIEW = "type_view";
    public static final String TYPE_DATA = "type_data";
    public static final String TYPE_GLOBAL = "type_global";

    public static Testable createTestable(JSONObject object) {
        if (object == null) {
            LogUtil.e(TAG, "createTestable with null object");
            return null;
        }
        if (TYPE_VIEW.equals(object.optString(Testable.TESTABLE_KEY))) {
            return TestableViewFactory.createViewData(object);
        }

        if (TYPE_GLOBAL.equals(object.optString(Testable.TESTABLE_KEY))) {
            return GlobalTestableFactory.createGlobalTestable(object);
        }

        if (TYPE_DATA.equals(object.optString(Testable.TESTABLE_KEY))) {
            return TestableDataFactory.createTestableData(object);
        }

        throw new IllegalArgumentException("createTestable return null, unrecognised instance, data is :" + object.toString());

    }
}
