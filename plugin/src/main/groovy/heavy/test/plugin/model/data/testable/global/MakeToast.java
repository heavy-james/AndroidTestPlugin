package heavy.test.plugin.model.data.testable.global;

import org.gradle.internal.impldep.com.google.gson.annotations.SerializedName;

/**
 * Created by heavy on 2017/6/1.
 */

public class MakeToast extends GlobalTestable {

    @SerializedName("toast_time")
    long toastTime = 2000;

    public MakeToast(String msg, long toastTime) {
        this.description = msg;
        this.toastTime = toastTime;
    }

    public String getToastMessage() {
        return description;
    }

    public void setToastMessage(String msg) {
        description = msg;
    }

    public long getToastTime() {
        return toastTime;
    }

    public void setToastTime(long toastTime) {
        this.toastTime = toastTime;
    }

}
