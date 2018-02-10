package heavy.test.plugin.model.data.action.view;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Action;

/**
 * Created by heavy on 2017/5/20.
 */

public class Check extends Action {

    @SerializedName("checked")
    boolean checked;

    public Check(boolean checked) {
        this.checked = checked;
    }
}
