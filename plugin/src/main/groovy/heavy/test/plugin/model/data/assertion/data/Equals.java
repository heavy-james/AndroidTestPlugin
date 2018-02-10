package heavy.test.plugin.model.data.assertion.data;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Assertion;
import heavy.test.plugin.model.data.Extra;

/**
 * Created by heavy on 2017/6/2.
 */

public class Equals extends Assertion {

    @SerializedName("ignore_case")
    boolean ignoreCase;

    public Equals(String content) {
        description = content;
    }

    public Equals(Object object) {
        this(object, false);
    }

    public Equals(Object content, boolean conversed) {
        this.extra = new Extra(content);
        this.conversed = conversed;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public String getContent() {
        return description;
    }
}
