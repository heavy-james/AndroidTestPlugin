package heavy.test.plugin.model.data.assertion.view;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Assertion;

/**
 * Created by heavy on 2017/5/20.
 */

public class Display extends Assertion {

    @SerializedName("displayed")
    boolean displayed;

    public Display(boolean displayed) {
        this.displayed = displayed;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }
}
