package heavy.test.plugin.model.wrapper.reflection;

import heavy.test.plugin.model.data.reflection.RuntimeValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heavy on 2017/6/4.
 */

public class MethodArgsWrapper {

    List<RuntimeValue> argObjects;

    public MethodArgsWrapper() {
        argObjects = new ArrayList<>();
    }

    void add(String value) {
        RuntimeValue runtimeValue = new RuntimeValue(value);
        argObjects.add(runtimeValue);
    }

    void add(int value) {
        RuntimeValue runtimeValue = new RuntimeValue(value);
        argObjects.add(runtimeValue);
    }

    void add(boolean value) {
        RuntimeValue runtimeValue = new RuntimeValue(value);
        argObjects.add(runtimeValue);
    }

    void add(double value) {
        RuntimeValue runtimeValue = new RuntimeValue(value);
        argObjects.add(runtimeValue);
    }

    void add(float value) {
        RuntimeValue runtimeValue = new RuntimeValue(value);
        argObjects.add(runtimeValue);
    }

    void add(RuntimeValue value) {
        argObjects.add(value);
    }

    public List<RuntimeValue> getArgObjects() {
        return argObjects;
    }
}
