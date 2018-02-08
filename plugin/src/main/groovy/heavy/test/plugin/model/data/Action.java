package heavy.test.plugin.model.data;

import heavy.test.plugin.model.data.factory.AtomFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/20.
 */

public abstract class Action extends Atom {

    public static final String TYPE_VIEW_ACTION = "view_action";
    public static final String TYPE_GLOBAL_ACTION = "global_action";
    public static final String TYPE_DATA_ACTION = "data_action";

    public abstract String getActionType();

    @Override
    public String getAtomType() {
        return AtomFactory.ATOM_TYPE_ACTION;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.putOpt("action_type", getActionType());
        return result;
    }
}
