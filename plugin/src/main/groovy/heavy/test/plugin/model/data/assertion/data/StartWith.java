package heavy.test.plugin.model.data.assertion.data;

import heavy.test.plugin.model.data.Assertion;

/**
 * Created by heavy on 2017/6/2.
 */

public class StartWith extends Assertion {

    public StartWith(String content) {
        description = content;
    }

    public String getContent() {
        return description;
    }
}
