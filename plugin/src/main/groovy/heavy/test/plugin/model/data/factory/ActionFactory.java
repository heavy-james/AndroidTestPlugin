package heavy.test.plugin.model.data.factory;

import heavy.test.plugin.model.data.Action;
import heavy.test.plugin.model.data.action.global.Delay;
import heavy.test.plugin.model.data.action.view.Check;
import heavy.test.plugin.model.data.action.view.Click;
import heavy.test.plugin.model.data.action.view.KeyEvent;
import heavy.test.plugin.model.data.action.view.KeyPress;
import heavy.test.plugin.model.data.action.view.Focus;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/21.
 */

public class ActionFactory {

    public static final String TAG = "ActionFactory";
    public static final String ACTION_CHECK = "check";
    public static final String ACTION_CLICK = "click";
    public static final String ACTION_FOCUS = "focus";
    public static final String ACTION_KEY_EVENT = "keyEvent";
    public static final String ACTION_KEY_PRESS = "keyPress";
    public static final String ACTION_DELAY = "delay";

    public static Action createAction(JSONObject object) {
        if (null != object) {
            Action action = createAction(object.optString("action_type"));
            if (action != null) {
                action.parseJsonObject(object);
            }
            return action;
        }
        return null;
    }

    public static Action createAction(String name) {
        if (ACTION_CHECK.equals(name)) {
            return new Check(false);
        }
        if (ACTION_CLICK.equals(name)) {
            return new Click();
        }
        if (ACTION_FOCUS.equals(name)) {
            return new Focus(false);
        }
        if (ACTION_KEY_EVENT.equals(name)) {
            return new KeyEvent(-1, null);
        }
        if (ACTION_KEY_PRESS.equals(name)) {
            return new KeyPress(-1);
        }

        if (ACTION_DELAY.equals(name)) {
            return new Delay(0);
        }
        throw new IllegalArgumentException("createAction create action with unknown action name : " + name);
    }
}
