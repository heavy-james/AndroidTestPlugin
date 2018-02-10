package heavy.test.plugin.model.data.testable.global;

import org.gradle.internal.impldep.com.google.gson.annotations.SerializedName;

/**
 * Created by heavy on 2017/6/1.
 */

public class Delay extends GlobalTestable {

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
