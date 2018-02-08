package com.ktcp.test.model.data;

import com.ktcp.test.model.data.factory.TestObjectFactory;
import com.ktcp.test.util.TextUtil;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/31.
 */

public abstract class Atom extends TestObject {

    public static final String ATOM_KEY = "atom_type";

    protected String description;

    public Atom() {
        objectType = TestObjectFactory.OBJECT_TYPE_ATOM;
    }

    public abstract String getAtomType();

    public String getDescription() {
        return description;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.putOpt(ATOM_KEY, getAtomType());
        if (!TextUtil.isEmpty(getDescription())) {
            result.putOpt("description", getDescription());
        }
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        description = object.optString("description");
    }

}
