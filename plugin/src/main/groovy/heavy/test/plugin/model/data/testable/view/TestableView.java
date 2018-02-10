package heavy.test.plugin.model.data.testable.view;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Identifier;
import heavy.test.plugin.model.data.Testable;

/**
 * Created by heavy on 2017/5/20.
 */

public class TestableView extends Testable {

    @SerializedName("position")
    int mPosition = -1;

    @SerializedName("identifier")
    Identifier mIdentifier;

    public TestableView() {
        mIdentifier = new Identifier();
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public Identifier getIdentifier() {
        return mIdentifier;
    }

    public void setIdentifier(Identifier mIdentifier) {
        this.mIdentifier = mIdentifier;
    }

}
