package heavy.test.plugin.model.wrapper.testable.global;

import heavy.test.plugin.model.data.testable.global.GlobalTestable;
import heavy.test.plugin.model.wrapper.TestObjectWrapper;

/**
 * Created by heavy on 2017/5/31.
 */

public class GlobalTestableWrapper extends TestObjectWrapper {

    GlobalTestable mGlobalTestable;

    public GlobalTestableWrapper(GlobalTestable globalTestable) {
        super(null, globalTestable);
        mGlobalTestable = globalTestable;
    }

    void description(String description) {
        mGlobalTestable.setDescription(description);
    }

    void message(String msg) {
        mGlobalTestable.setDescription(msg);
    }

    public GlobalTestable getGlobalTestable() {
        return mGlobalTestable;
    }
}
