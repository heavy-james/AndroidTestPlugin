package com.ktcp.test.model.data.assertion.data;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.factory.AssertionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/2.
 */

public class Contains extends Assertion {

    boolean conversed;

    public Contains(String content) {
        description = content;
    }

    public Contains(String content, boolean conversed) {
        description = content;
        this.conversed = conversed;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.DATA_ASSERTION_CONTAINS;
    }

    public String getContent() {
        return description;
    }

    public boolean isConversed() {
        return conversed;
    }

    @Override
    public JSONObject getJsonObject() {
        if (conversed) {
            return super.getJsonObject().putOpt("conversed", conversed);
        }
        return super.getJsonObject();
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        conversed = object.optBoolean("conversed");
    }
}
