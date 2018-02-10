package heavy.test.plugin.model.data.testable.global;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.IntentData;

/**
 * Created by heavy on 2017/6/20.
 */

public class SetUpActivity extends GlobalTestable {

    @SerializedName("intent_data")
    private IntentData intentData;

    public SetUpActivity(IntentData intentData) {
        this.intentData = intentData;
    }

    public IntentData getIntentData() {
        return intentData;
    }

    public void setIntentData(IntentData intentData) {
        this.intentData = intentData;
    }
}
