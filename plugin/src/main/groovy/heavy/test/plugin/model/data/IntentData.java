package heavy.test.plugin.model.data;

import heavy.test.plugin.model.data.interf.IJsonable;
import heavy.test.plugin.util.TextUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heavy on 2017/5/20.
 */

public class IntentData implements IJsonable {
    String action;
    String className;
    String packageName;
    String category;
    int flag = -1;
    List<Extra> extras;

    public IntentData() {
        extras = new ArrayList<>();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = new JSONObject();
        if (!TextUtil.isEmpty(action)) {
            result.putOpt("action", action);
        }
        if (!TextUtil.isEmpty(className)) {
            result.putOpt("className", className);
        }
        if (!TextUtil.isEmpty(category)) {
            result.putOpt("category", category);
        }
        if (flag > -1) {
            result.putOpt("flag", flag);
        }
        if (!TextUtil.isEmpty(packageName)) {
            result.putOpt("packageName", packageName);
        }
        if (null != extras && extras.size() > 0) {
            JSONArray extrasArray = new JSONArray();
            for (Extra extra : extras) {
                extrasArray.put(extra.getJsonObject());
            }
            result.putOpt("extras", extrasArray);
        }
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject jsonObject) {
        action = jsonObject.optString("action");
        packageName = jsonObject.optString("packageName");
        className = jsonObject.optString("className");
        category = jsonObject.optString("category");
        flag = jsonObject.optInt("flag", -1);
        JSONArray extrasArray = jsonObject.optJSONArray("extras");
        if (null != extrasArray) {
            for (int i = 0; i < extrasArray.length(); i++) {
                Extra extra = new Extra();
                extra.parseJsonObject(extrasArray.optJSONObject(i));
                extras.add(extra);
            }
        }
    }

}
