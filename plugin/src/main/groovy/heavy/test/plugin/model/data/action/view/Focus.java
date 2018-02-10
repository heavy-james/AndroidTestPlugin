package heavy.test.plugin.model.data.action.view;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Action;

/**
 * Created by heavy on 2017/5/20.
 */

public class Focus extends Action {

    @SerializedName("focused")
    boolean focused;

    public Focus(boolean focused) {
        this.focused = focused;
    }

    public boolean hasFocus() {
        return focused;
    }
}
