package heavy.test.plugin.model.data.action.view;

import heavy.test.plugin.model.data.Action;
import heavy.test.plugin.model.data.factory.ActionFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/20.
 */

public class Check extends Action {

    boolean checked;

    public Check(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getActionType() {
        return ActionFactory.ACTION_CHECK;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject object = super.getJsonObject();
        object.putOpt("checked", checked);
        return object;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        checked = object.optBoolean("checked");
    }
}
