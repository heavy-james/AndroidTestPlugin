package heavy.test.plugin.model.data.testable.global;

import heavy.test.plugin.model.data.Testable;

/**
 * Created by heavy on 2017/5/31.
 */

public abstract class GlobalTestable extends Testable {

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
