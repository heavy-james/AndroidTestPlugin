package com.ktcp.test.model.data.testable.view;

import com.ktcp.test.model.data.Identifier;
import com.ktcp.test.model.data.Testable;
import com.ktcp.test.model.data.factory.TestableFactory;
import com.ktcp.test.model.data.factory.TestableViewFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/20.
 */

public class TestableView extends Testable {

    public static final String TESTABLE_VIEW_KEY = "testable_view_type";

    protected String mType = TestableViewFactory.TYPE_VIEW;
    int mPosition = -1;
    Identifier mIdentifier;

    public TestableView() {
        mIdentifier = new Identifier();
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public Identifier getIdentifier() {
        return mIdentifier;
    }

    public void setIdentifier(Identifier mIdentifier) {
        this.mIdentifier = mIdentifier;
    }

    @Override
    public String getTestableType() {
        return TestableFactory.TYPE_VIEW;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject jsonObject = super.getJsonObject();
        jsonObject.put(TESTABLE_VIEW_KEY, mType);
        if (mPosition > 0) {
            jsonObject.putOpt("position", mPosition);
        }
        if (mIdentifier.getJsonObject().length() > 0) {
            jsonObject.putOpt("identifier", mIdentifier.getJsonObject());
        }
        return jsonObject;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        mType = object.optString(TESTABLE_VIEW_KEY);
        mPosition = object.optInt("position");
        if (null != object.optJSONObject("identifier")) {
            mIdentifier.parseJsonObject(object.optJSONObject("identifier"));
        }
    }
}
