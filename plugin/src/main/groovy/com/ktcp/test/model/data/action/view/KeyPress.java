package com.ktcp.test.model.data.action.view;

import com.ktcp.test.model.data.Action;
import com.ktcp.test.model.data.factory.ActionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/20.
 */

public class KeyPress extends Action {

    public int keyCode;

    public KeyPress(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public String getActionType() {
        return ActionFactory.ACTION_KEY_PRESS;
    }

    public int getKeyCode() {
        return keyCode;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.putOpt("keyCode", keyCode);
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        keyCode = object.optInt("keyCode");
    }
}
