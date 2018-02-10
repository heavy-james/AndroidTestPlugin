package heavy.test.plugin.model.data.action.view;

import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Action;

/**
 * Created by heavy on 2017/5/20.
 */

public class KeyEvent extends Action {

    @SerializedName("key_code")
    public int keyCode;
    @SerializedName("event_action")
    public String eventAction;

    public KeyEvent(int keyCode, String eventAction) {
        this.keyCode = keyCode;
        this.eventAction = eventAction;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public String getEventAction() {
        return eventAction;
    }
}
