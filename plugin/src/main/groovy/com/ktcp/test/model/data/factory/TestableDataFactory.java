package com.ktcp.test.model.data.factory;

import com.ktcp.test.model.data.testable.data.TestableData;
import com.ktcp.test.model.data.testable.data.TestableField;
import com.ktcp.test.model.data.testable.data.TestableMethod;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/2.
 */

public class TestableDataFactory {

    public static final String TAG = "TestableDataFactory";
    public static final String TYPE_FIELD = "type_field";
    public static final String TYPE_METHOD = "type_method";

    public static TestableData createTestableData(JSONObject object) {
        if (object == null) {
            return null;
        }
        if (TYPE_FIELD.equals(object.optString(TestableData.TESTABLE_DATA_KEY))) {
            TestableData testableData = new TestableField();
            testableData.parseJsonObject(object);
            return testableData;
        }

        if (TYPE_METHOD.equals(object.optString(TestableData.TESTABLE_DATA_KEY))) {
            TestableData testableData = new TestableMethod();
            testableData.parseJsonObject(object);
            return testableData;
        }
        throw new IllegalArgumentException("createTestableData return null, unknown type, data is :" + object.toString());
    }
}
