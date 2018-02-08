package heavy.test.plugin.logic.command;

import heavy.test.plugin.logic.TestCommand;
import heavy.test.plugin.model.data.reflection.RuntimeValue;
import heavy.test.plugin.logic.TestCommandFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 17/6/29.
 */

public class GetRuntimeValue extends TestCommand {


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

    @Override
    public String getName() {
        return TestCommandFactory.GET_RUNTIME_VALUE;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        mRuntimeValue = new RuntimeValue();
        mRuntimeValue.parseJsonObject(object.optJSONObject("runtime_value"));
    }

    @Override
    public JSONObject getJsonObject() {
        return super.getJsonObject().put("runtime_value", mRuntimeValue.getJsonObject());

    }
}
