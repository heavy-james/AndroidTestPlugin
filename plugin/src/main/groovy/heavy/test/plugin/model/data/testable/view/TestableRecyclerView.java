package heavy.test.plugin.model.data.testable.view;

import heavy.test.plugin.model.data.factory.TestableViewFactory;

import org.json.JSONObject;


/**
 * Created by heavy on 2017/5/20.
 */

public class TestableRecyclerView extends TestableAdapterView {

    public TestableRecyclerView() {
        mType = TestableViewFactory.TYPE_RECYCLER_VIEW;
    }

    @Override
    public JSONObject getJsonObject() {
        return super.getJsonObject();
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
    }
}
