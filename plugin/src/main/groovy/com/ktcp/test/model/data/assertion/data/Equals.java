package com.ktcp.test.model.data.assertion.data;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.Extra;
import com.ktcp.test.model.data.factory.AssertionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/2.
 */

public class Equals extends Assertion {

    boolean ignoreCase;
    boolean conversed;

    public Equals(String content) {
        description = content;
    }

    public Equals(Object object) {
        this(object, false);
    }

    public Equals(Object content, boolean conversed) {
        this.extra = new Extra(content);
        this.conversed = conversed;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public boolean isConversed() {
        return conversed;
    }

    public void setConversed(boolean conversed) {
        this.conversed = conversed;
    }


    @Override
    public String getAssertionType() {
        return AssertionFactory.DATA_ASSERTION_EQUALS;
    }

    public String getContent() {
        return description;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        if (ignoreCase) {
            result.putOpt("ignore_case", ignoreCase);
        }
        if (conversed) {
            result.putOpt("conversed", conversed);
        }
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        ignoreCase = object.optBoolean("ignore_case");
        conversed = object.optBoolean("conversed");
    }
}
