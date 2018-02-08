package heavy.test.plugin.model.data.assertion.view;

import heavy.test.plugin.model.data.Assertion;
import heavy.test.plugin.model.data.factory.AssertionFactory;

/**
 * Created by heavy on 2017/5/20.
 */

public class WithText extends Assertion {

    public WithText(String content) {
        description = content;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.VIEW_ASSERTION_WITH_TEXT;
    }

}
