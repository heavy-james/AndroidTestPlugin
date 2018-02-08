package heavy.test.plugin.model.data.testable.view;

import heavy.test.plugin.model.data.factory.TestableViewFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heavy on 2017/5/20.
 */

public class TestableAdapterView extends TestableView {
    String mClassName;
    List<TestableView> mChildTestableViews;

    public TestableAdapterView() {
        mType = TestableViewFactory.TYPE_ADAPTER_VIEW;
        mChildTestableViews = new ArrayList<TestableView>();
    }

    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String className) {
        mClassName = className;
    }

    public List<TestableView> getChildView() {
        return mChildTestableViews;
    }

    public void setChildView(List<TestableView> childTestableViews) {
        mChildTestableViews = childTestableViews;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        if (mChildTestableViews.size() > 0) {
            JSONArray childArray = new JSONArray();
            for (TestableView testableView : mChildTestableViews) {
                childArray.put(testableView.getJsonObject());
            }
            result.putOpt("children", childArray);
        }
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);

        JSONArray childArray = object.optJSONArray("children");
        if (childArray != null && childArray.length() > 0) {
            for (int i = 0; i < childArray.length(); i++) {
                TestableView child = new TestableView();
                child.parseJsonObject(childArray.getJSONObject(i));
                mChildTestableViews.add(child);
            }
        }
    }
}


