package com.ktcp.test.model.data.action.view;

import com.ktcp.test.model.data.Action;
import com.ktcp.test.model.data.factory.ActionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/20.
 */

public class Focus extends Action {

    boolean focused;

    public Focus(boolean focused) {
        this.focused = focused;
    }

    public boolean hasFocus() {
        return focused;
    }

    @Override
    public String getActionType() {
        return ActionFactory.ACTION_FOCUS;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject object = super.getJsonObject();
        object.putOpt("value", focused);
        return object;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        focused = object.optBoolean("value");
    }
}
