package heavy.test.plugin.model.data.testable.view;

import org.gradle.internal.impldep.com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heavy on 2017/5/20.
 */

public class TestableAdapterView extends TestableView {
    @SerializedName("class_name")
    String mClassName;
    @SerializedName("child_views")
    List<TestableView> mChildTestableViews;

    public TestableAdapterView() {
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
}


