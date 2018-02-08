package com.ktcp.test.model.data.assertion.data;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.factory.AssertionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/2.
 */

public class NullCheck extends Assertion {

    boolean isNull;

    public NullCheck(boolean isNull) {
        this.isNull = isNull;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.DATA_ASSERTION_NULL_CHECK;
    }

    public boolean isNull() {
        return isNull;
    }

    @Override
    public JSONObject getJsonObject() {
        return super.getJsonObject().put("isNull", isNull);
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        isNull = object.optBoolean("isNull");
    }
}
