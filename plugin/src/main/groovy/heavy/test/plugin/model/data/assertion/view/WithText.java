package heavy.test.plugin.model.data.assertion.view;

import heavy.test.plugin.model.data.Assertion;

/**
 * Created by heavy on 2017/5/20.
 */

public class WithText extends Assertion {

    public WithText(String content) {
        description = content;
    }
}
