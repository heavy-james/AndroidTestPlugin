package heavy.test.plugin.model.data.assertion.view;

import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Assertion;


/**
 * Created by heavy on 2017/5/31.
 */

public class HasFocus extends Assertion {

    @SerializedName("has_focus")
    boolean hasFocus;

    public HasFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
    }

    public boolean isHasFocus() {
        return hasFocus;
    }
}
