package com.ktcp.test.model.data.assertion.view;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.factory.AssertionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/24.
 */

public class FullScreen extends Assertion {

    boolean isFullScreen;

    public FullScreen(boolean full) {
        isFullScreen = full;
    }

    public boolean isFull() {
        return isFullScreen;
    }

    public void setFull(boolean full) {
        isFullScreen = full;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.VIEW_ASSERTION_IS_FULL_SCREEN;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.putOpt("isFullScreen", isFullScreen);
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        isFullScreen = object.optBoolean("isFullScreen");
    }
}
