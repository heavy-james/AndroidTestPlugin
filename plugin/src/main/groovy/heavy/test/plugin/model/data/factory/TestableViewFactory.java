package heavy.test.plugin.model.data.factory;

import heavy.test.plugin.model.data.testable.view.TestableAdapterView;
import heavy.test.plugin.model.data.testable.view.TestableRecyclerView;
import heavy.test.plugin.model.data.testable.view.TestableView;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/21.
 */

public class TestableViewFactory {

    public static final String TAG = "TestableViewFactory";

    public static final String TYPE_VIEW = "testableView";
    public static final String TYPE_ADAPTER_VIEW = "testableAdapterView";
    public static final String TYPE_RECYCLER_VIEW = "testableRecyclerView";

    public static TestableView createViewData(JSONObject object) {
        TestableView result = createViewData(object.optString(TestableView.TESTABLE_VIEW_KEY));
        if (result != null) {
            result.parseJsonObject(object);
        }
        return result;
    }

    public static TestableView createViewData(String type) {
        if (TYPE_VIEW.equals(type)) {
            return new TestableView();
        }
        if (TYPE_ADAPTER_VIEW.equals(type)) {
            return new TestableAdapterView();
        }
        if (TYPE_RECYCLER_VIEW.equals(type)) {
            return new TestableRecyclerView();
        }

        throw new IllegalArgumentException("createTestable return null, unrecognised type : " + type);
    }
}
