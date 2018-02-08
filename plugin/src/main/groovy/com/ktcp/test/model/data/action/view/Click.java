package com.ktcp.test.model.data.action.view;

import com.ktcp.test.model.data.Action;
import com.ktcp.test.model.data.factory.ActionFactory;

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
