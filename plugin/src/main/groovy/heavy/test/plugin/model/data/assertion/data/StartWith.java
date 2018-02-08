package heavy.test.plugin.model.data.assertion.data;

import heavy.test.plugin.model.data.Assertion;
import heavy.test.plugin.model.data.factory.AssertionFactory;

/**
 * Created by heavy on 2017/6/2.
 */

public class StartWith extends Assertion {

    public StartWith(String content) {
        description = content;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.DATA_ASSERTION_START_WITH;
    }

    public String getContent() {
        return description;
    }
}
