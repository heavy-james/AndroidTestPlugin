package heavy.test.plugin.model.data.action.view;

import heavy.test.plugin.model.data.Action;
import heavy.test.plugin.model.data.factory.ActionFactory;

/**
 * Created by heavy on 2017/5/20.
 */

public class Click extends Action {
    public Click() {
        description = "true";
    }

    @Override
    public String getActionType() {
        return ActionFactory.ACTION_CLICK;
    }

}
