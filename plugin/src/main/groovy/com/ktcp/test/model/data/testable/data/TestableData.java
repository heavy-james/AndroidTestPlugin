package com.ktcp.test.model.data.testable.data;

import com.ktcp.test.model.data.Testable;
import com.ktcp.test.model.data.factory.TestableFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/2.
 */

public abstract class TestableData extends Testable {

    public static final String TESTABLE_DATA_KEY = "testable_data_type";

    @Override
    public String getTestableType() {
        return TestableFactory.TYPE_DATA;
    }

    public abstract String getTestableDataType();

    @Override
    public JSONObject getJsonObject() {
        return super.getJsonObject().put(TESTABLE_DATA_KEY, getTestableDataType());
    }
}
