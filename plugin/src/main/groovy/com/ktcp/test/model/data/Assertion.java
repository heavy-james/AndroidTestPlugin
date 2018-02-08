package com.ktcp.test.model.data;

import com.ktcp.test.model.data.factory.AtomFactory;
import com.ktcp.test.model.data.reflection.RuntimeValue;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/31.
 */

public abstract class Assertion extends Atom {

    protected Extra extra;

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(RuntimeValue extra) {
        this.extra = extra;
    }

    @Override
    public String getAtomType() {
        return AtomFactory.ATOM_TYPE_ASSERTION;
    }

    public abstract String getAssertionType();

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.putOpt("assertion_type", getAssertionType());
        if (extra != null) {
            result.putOpt("runtime_value", extra.getJsonObject());
        }
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        JSONObject runtimeObject = object.optJSONObject("runtime_value");
        if (runtimeObject != null) {
            extra = new RuntimeValue();
            extra.parseJsonObject(runtimeObject);
        }
    }
}
