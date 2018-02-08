package com.ktcp.test.model.data.assertion.view;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.factory.AssertionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/20.
 */

public class Display extends Assertion {

    boolean displayed;

    public Display(boolean displayed) {
        this.displayed = displayed;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.VIEW_ASSERTION_DISPLAY;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.putOpt("displayed", displayed);
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        displayed = object.optBoolean("displayed");
    }
}
