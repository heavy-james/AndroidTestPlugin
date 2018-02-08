package com.ktcp.test.model.data.action.global;

import com.ktcp.test.model.data.Action;
import com.ktcp.test.model.data.factory.ActionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/24.
 */

public class Delay extends Action {

    long delayMillis;

    public Delay(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public long getDelayMillis() {
        return delayMillis;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    @Override
    public String getActionType() {
        return ActionFactory.ACTION_DELAY;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.putOpt("delayMillis", delayMillis);
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        delayMillis = object.optInt("delayMillis");
    }
}
