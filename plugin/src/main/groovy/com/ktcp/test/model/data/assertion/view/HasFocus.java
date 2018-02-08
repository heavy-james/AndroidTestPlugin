package com.ktcp.test.model.data.assertion.view;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.factory.AssertionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/31.
 */

public class HasFocus extends Assertion {

    boolean hasFocus;

    public HasFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.VIEW_ASSERTION_HAS_FOCUS;
    }

    public boolean isHasFocus() {
        return hasFocus;
    }

    @Override
    public JSONObject getJsonObject() {
        return super.getJsonObject().put("hasFocus", hasFocus);
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        hasFocus = object.optBoolean("hasFocus");
    }
}
