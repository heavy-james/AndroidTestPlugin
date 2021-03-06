package heavy.test.plugin.model.data.action.global;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Action;

/**
 * Created by heavy on 2017/5/24.
 */

public class Delay extends Action {

    @SerializedName("delay_millis")
    long delayMillis;

    public Delay(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public long getDelayMillis() {
        return delayMillis;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }
}
