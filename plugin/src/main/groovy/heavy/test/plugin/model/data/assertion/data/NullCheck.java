package heavy.test.plugin.model.data.assertion.data;

import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Assertion;


/**
 * Created by heavy on 2017/6/2.
 */

public class NullCheck extends Assertion {

    @SerializedName("is_null")
    boolean isNull;

    public NullCheck(boolean isNull) {
        this.isNull = isNull;
    }

    public boolean isNull() {
        return isNull;
    }
}
