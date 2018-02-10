package heavy.test.plugin.model.data.testable.global;

import org.gradle.internal.impldep.com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.reflection.RuntimeValue;

/**
 * Created by heavy on 17/6/29.
 */

public class GetRuntimeValue extends GlobalTestable {


    @SerializedName("runtime_value")
    RuntimeValue mRuntimeValue;

    public GetRuntimeValue(RuntimeValue runtimeValue) {
        mRuntimeValue = runtimeValue;
    }

    public RuntimeValue getRuntimeValue() {
        return mRuntimeValue;
    }

    public void setRuntimeValue(RuntimeValue mRuntimeValue) {
        this.mRuntimeValue = mRuntimeValue;
    }
}
