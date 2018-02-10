package heavy.test.plugin.model.data.reflection;

import org.json.JSONObject;

import heavy.test.plugin.model.data.interf.IJsonable;
import heavy.test.plugin.util.TextUtil;

/**
 * Created by heavy on 2017/6/2.
 */

public class ObjectData implements IJsonable {

    public static final String FROM_ACTIVITY = "from_activity";

    ObjectData container;
    String containerName;
    String containerClassName;
    String objectName;
    boolean isStatic;

    public ObjectData getContainer() {
        return container;
    }

    public void setContainer(ObjectData container) {
        this.container = container;
    }

    public String getContainerClassName() {
        return containerClassName;
    }

    public void setContainerClassName(String containerClassName) {
        this.containerClassName = containerClassName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        if (object == null) {
            return;
        }
        containerName = object.optString("container_name");
        JSONObject containerObject = object.optJSONObject("container");
        if (containerObject != null) {
            container = new ObjectData();
            container.parseJsonObject(containerObject);
        }
        objectName = object.optString("object_name");
        isStatic = object.optBoolean("is_static");
        containerClassName = object.optString("container_class_name");
        JSONObject methodObject = object.optJSONObject("source_method");
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = new JSONObject();
        if (container != null) {
            result.putOpt("container", container.getJsonObject());
        }
        if (!TextUtil.isEmpty(containerClassName)) {
            result.putOpt("container_class_name", containerClassName);
        }
        if (!TextUtil.isEmpty(containerName)) {
            result.putOpt("container_name", containerName);
        }
        if (!TextUtil.isEmpty(objectName)) {
            result.putOpt("object_name", objectName);
        }
        if (isStatic) {
            result.put("is_static", isStatic);
        }
        return result;
    }
}
