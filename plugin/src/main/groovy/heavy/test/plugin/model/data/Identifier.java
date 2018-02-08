package heavy.test.plugin.model.data;

import heavy.test.plugin.model.data.interf.IJsonable;
import heavy.test.plugin.util.TextUtil;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/20.
 */

public class Identifier implements IJsonable {
    String id;
    String description;
    String viewType;

    Identifier parentIdentifier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public Identifier getParentIdentifier() {
        return parentIdentifier;
    }

    public void setParentIdentifier(Identifier parentIdentifier) {
        this.parentIdentifier = parentIdentifier;
    }

    public boolean isEmpty() {
        boolean result = TextUtil.isEmpty(id) && TextUtil.isEmpty(description);
        result &= parentIdentifier == null || parentIdentifier.isEmpty();
        return result;
    }

    @Override
    public int hashCode() {
        int factor = 1;

        if (null != id) {
            factor *= id.hashCode();
        }
        if (null != description) {
            factor *= description.hashCode();
        }
        if (null != parentIdentifier) {
            factor *= parentIdentifier.hashCode();
        }
        return factor;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Identifier && o.hashCode() == hashCode();
    }

    public JSONObject getJsonObject() {
        JSONObject result = new JSONObject();
        if (!TextUtil.isEmpty(id)) {
            result.putOpt("id", id);
        }
        if (!TextUtil.isEmpty(description)) {
            result.putOpt("description", description);
        }
        if (null != parentIdentifier) {
            result.putOpt("parentIdentifier", parentIdentifier.getJsonObject());
        }
        return result;
    }

    public void parseJsonObject(JSONObject object) {
        id = object.optString("id");
        description = object.optString("description");
        if (null != object.optJSONObject("parentIdentifier")) {
            parentIdentifier = new Identifier();
            parentIdentifier.parseJsonObject(object.optJSONObject("parentIdentifier"));
        }
    }
}
