package heavy.test.plugin.model.data.assertion.view;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Assertion;

/**
 * Created by heavy on 2017/5/24.
 */

public class FullScreen extends Assertion {

    @SerializedName("is_full_screen")
    boolean isFullScreen;

    public FullScreen(boolean full) {
        isFullScreen = full;
    }

    public boolean isFull() {
        return isFullScreen;
    }

    public void setFull(boolean full) {
        isFullScreen = full;
    }
}
