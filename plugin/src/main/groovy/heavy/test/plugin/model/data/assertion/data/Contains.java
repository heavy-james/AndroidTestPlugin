package heavy.test.plugin.model.data.assertion.data;

import heavy.test.plugin.model.data.Assertion;

/**
 * Created by heavy on 2017/6/2.
 */

public class Contains extends Assertion {

    public Contains(String content) {
        description = content;
    }

    public Contains(String content, boolean conversed) {
        description = content;
        this.conversed = conversed;
    }

    public String getContent() {
        return description;
    }
}
