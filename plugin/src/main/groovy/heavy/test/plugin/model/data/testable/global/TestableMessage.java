package heavy.test.plugin.model.data.testable.global;

import heavy.test.plugin.model.data.factory.GlobalTestableFactory;

/**
 * Created by heavy on 2017/6/1.
 */

public class TestableMessage extends GlobalTestable {

    public TestableMessage() {

    }

    public TestableMessage(String msg) {
        description = msg;
    }

    @Override
    public String getGlobalTestableType() {
        return GlobalTestableFactory.TYPE_MESSAGE;
    }

    public String getMessage() {
        return description;
    }

    public void setMessage(String msg) {
        description = msg;
    }
}
