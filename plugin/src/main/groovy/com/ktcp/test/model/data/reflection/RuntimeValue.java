package com.ktcp.test.model.data.reflection;

import com.ktcp.test.logic.TestManager;
import com.ktcp.test.model.data.Extra;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/3.
 */

public class RuntimeValue extends Extra {

    boolean fromMethod;

    ObjectData objectData;

    MethodData methodData;

    public RuntimeValue() {
        this(null);
    }

    public RuntimeValue(Object value) {
        super(value);
    }

    public ObjectData getObjectData() {
        return objectData;
    }

    public void setObjectData(ObjectData objectData) {
        this.objectData = objectData;
    }

    public MethodData getMethodData() {
        return methodData;
    }

    public void setMethodData(MethodData methodData) {
        this.methodData = methodData;
    }

    public boolean isFromMethod() {
        return fromMethod;
    }

    public void setFromMethod(boolean fromMethod) {
        this.fromMethod = fromMethod;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        if (object == null) {
            return;
        }
        super.parseJsonObject(object);
        fromMethod = object.optBoolean("from_method");
        if (object.optJSONObject("object_data") != null) {
            objectData = new ObjectData();
            objectData.parseJsonObject(object.optJSONObject("object_data"));
        }
        if (object.optJSONObject("method_data") != null) {
            methodData = new MethodData();
            methodData.parseJsonObject(object.optJSONObject("method_data"));
        }
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.put("from_method", fromMethod);
        if (objectData != null) {
            result.put("object_data", objectData.getJsonObject());
        }
        if (methodData != null) {
            result.put("method_data", methodData.getJsonObject());
        }
        return result;
    }

    public RuntimeValue resolve() {
        TestManager.getInstance().resolveRuntimeValue(this);
        return this;
    }

}
