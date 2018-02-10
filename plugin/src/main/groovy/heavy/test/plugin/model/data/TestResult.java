package heavy.test.plugin.model.data;

import org.gradle.internal.impldep.com.google.gson.annotations.SerializedName;

/**
 * Created by heavy on 2018/2/9.
 */

public class TestResult extends TestObject {

    @SerializedName("failed")
    boolean failed;

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }
}
