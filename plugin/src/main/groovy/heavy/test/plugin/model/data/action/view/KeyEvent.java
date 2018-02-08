package heavy.test.plugin.model.data.action.view;

import heavy.test.plugin.model.data.Action;
import heavy.test.plugin.model.data.factory.ActionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/20.
 */

public class KeyEvent extends Action {

    public int keyCode;
    public String eventAction;

    public KeyEvent(int keyCode, String eventAction) {
        this.keyCode = keyCode;
        this.eventAction = eventAction;
    }

    @Override
    public String getActionType() {
        return ActionFactory.ACTION_KEY_EVENT;
    }

    @Override
    public String getDescription() {
        return null;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public String getEventAction() {
        return eventAction;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        JSONObject value = new JSONObject();
        value.putOpt("keyCode", keyCode);
        value.putOpt("eventAction", eventAction);
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        keyCode = object.optInt("keyCode");
        eventAction = object.optString("eventAction");
    }
}
