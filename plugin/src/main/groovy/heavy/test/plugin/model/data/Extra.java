package heavy.test.plugin.model.data;

import heavy.test.plugin.util.TextUtil;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/20.
 */

public class Extra {


    protected String key;
    protected String type;
    protected String value;

    public Extra() {

    }

    public Extra(Object value) {
        this(null, value);
    }

    public Extra(String key, Object value) {
        if (key != null || value != null) {
            this.key = key;
            this.type = getType(value);
            this.value = String.valueOf(value);
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInt() {
        return Integer.parseInt(value);
    }

    public long getLong() {
        return Long.parseLong(value);
    }

    public String getString() {
        return value;
    }

    public boolean getBoolean() {
        return Boolean.parseBoolean(value);
    }

    public float getFloat() {
        return Float.parseFloat(value);
    }

    public double getDouble() {
        return Double.parseDouble(value);
    }

    public boolean isInt() {
        return "int".equals(type);
    }

    public boolean isBoolean() {
        return "boolean".equals(type);
    }

    public boolean isLong() {
        return "long".equals(type);
    }

    public boolean isFloat() {
        return "float".equals(type);
    }

    public boolean isDouble() {
        return "double".equals(type);
    }

    public boolean isString() {
        return "string".equals(type);
    }

    public boolean isNull() {
        return "null".equals(type);
    }

    /**
     * get the real type value
     *
     * @return
     */
    public Object getValue() {
        if (isNull()) {
            return null;
        }
        if (isString()) {
            return getString();
        }
        if (isInt()) {
            return getInt();
        }
        if (isLong()) {
            return getLong();
        }
        if (isFloat()) {
            return getFloat();
        }
        if (isDouble()) {
            return getDouble();
        }
        return value;
    }

    public void setValue(Object value) {
        this.value = value.toString();
    }

    public JSONObject getJsonObject() {
        JSONObject result = new JSONObject();
        if (!TextUtil.isEmpty(key)) {
            result.putOpt("key", key);
        }
        if (!TextUtil.isEmpty(type)) {
            result.putOpt("type", type);
        }
        if (!TextUtil.isEmpty(value)) {
            result.putOpt("value", value);
        }
        return result;
    }

    public void parseJsonObject(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        key = jsonObject.optString("key");
        value = jsonObject.optString("value");
        type = jsonObject.optString("type");
    }

    String getType(Object data) {
        if (data == null) {
            return "null";
        }
        if (data instanceof Integer) {
            return "int";
        }
        if (data instanceof Long) {
            return "long";
        }
        if (data instanceof Float) {
            return "float";
        }
        if (data instanceof Double) {
            return "double";
        }
        if (data instanceof String) {
            return "string";
        }
        if (data instanceof Boolean) {
            return "boolean";
        }
        return null;
    }
}
