package heavy.test.plugin.model.data.action.view;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Action;

/**
 * Created by heavy on 2017/5/20.
 */

public class KeyPress extends Action {

    @SerializedName("key_code")
    public int keyCode;

    public KeyPress(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
