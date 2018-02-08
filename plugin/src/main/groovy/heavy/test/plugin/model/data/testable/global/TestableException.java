package heavy.test.plugin.model.data.testable.global;

import heavy.test.plugin.model.data.factory.GlobalTestableFactory;

/**
 * Created by heavy on 2017/6/1.
 */

public class TestableException extends GlobalTestable {

    public TestableException() {

    }

    public TestableException(String msg) {
        description = msg;
    }

    @Override
    public String getGlobalTestableType() {
        return GlobalTestableFactory.TYPE_EXCEPTION;
    }

    public String getExceptionMessage() {
        return description;
    }
}