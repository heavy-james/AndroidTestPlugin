package heavy.test.plugin.model.data.reflection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import heavy.test.plugin.model.data.interf.IJsonable;
import heavy.test.plugin.util.TextUtil;

/**
 * Created by heavy on 2017/6/2.
 */

public class MethodData implements IJsonable {

    ObjectData container;
    String containerName;
    String containerClassName;
    String methodName;
    List<RuntimeValue> argList;
    List<String> argNameList;
    boolean isStatic;
    boolean isConstructor;

    public ObjectData getContainer() {
        return container;
    }

    public void setContainer(ObjectData container) {
        this.container = container;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public List<String> getArgNameList() {
        return argNameList;
    }

    public void setArgNameList(List<String> argNameList) {
        this.argNameList = argNameList;
    }

    public List<RuntimeValue> getArgList() {
        return argList;
    }

    public void setArgList(List<RuntimeValue> argList) {
        this.argList = argList;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public void setConstructor(boolean constructor) {
        isConstructor = constructor;
    }

    public String getContainerClassName() {
        return containerClassName;
    }

    public void setContainerClassName(String containerClassName) {
        this.containerClassName = containerClassName;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        methodName = object.optString("method_name");
        JSONObject containerObject = object.optJSONObject("container");
        if (containerObject != null) {
            container = new ObjectData();
            container.parseJsonObject(containerObject);
        }
        containerName = object.optString("container_name");
        JSONArray argArray = object.optJSONArray("args");
        if (argArray != null && argArray.length() > 0) {
            argList = new ArrayList<>();
            for (int i = 0; i < argArray.length(); i++) {
                RuntimeValue value = new RuntimeValue();
                value.parseJsonObject(argArray.getJSONObject(i));
                argList.add(value);
            }
        }
        JSONArray argNameArray = object.optJSONArray("argNames");
        if (argNameArray != null && argNameArray.length() > 0) {
            argNameList = new ArrayList<>();
            for (int i = 0; i < argNameArray.length(); i++) {
                argNameList.add(argNameArray.getString(i));
            }
        }
        isStatic = object.optBoolean("is_static");
        isConstructor = object.optBoolean("is_constructor");
        containerClassName = object.optString("container_class_name");
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = new JSONObject();
        if (container != null) {
            result.putOpt("container", container.getJsonObject());
        }
        if (!TextUtil.isEmpty(containerName)) {
            result.putOpt("container_name", containerName);
        }
        if (!TextUtil.isEmpty(methodName)) {
            result.put("method_name", methodName);
        }
        if (argList != null && argList.size() > 0) {
            JSONArray argArray = new JSONArray();
            for (RuntimeValue data : argList) {
                argArray.put(data.getJsonObject());
            }
            result.put("args", argArray);
        }
        if (argNameList != null && argNameList.size() > 0) {
            JSONArray argArray = new JSONArray();
            for (String argName : argNameList) {
                argArray.put(argName);
            }
            result.put("argNames", argArray);
        }
        if (isStatic) {
            result.put("is_static", isStatic);
        }
        if (isConstructor) {
            result.put("is_constructor", isConstructor);
        }
        if (!TextUtil.isEmpty(containerClassName)) {
            result.putOpt("container_class_name", containerClassName);
        }
        return result;
    }
}
