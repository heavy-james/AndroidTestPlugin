package heavy.test.plugin.model.data;

import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.reflection.RuntimeValue;


/**
 * Created by heavy on 2017/5/31.
 */

public abstract class Assertion extends Atom {

    @SerializedName("conversed")
    protected boolean conversed;

    @SerializedName("extra")
    protected Extra extra;

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(RuntimeValue extra) {
        this.extra = extra;
    }

    public boolean isConversed() {
        return conversed;
    }

    public void setConversed(boolean conversed) {
        this.conversed = conversed;
    }
}
