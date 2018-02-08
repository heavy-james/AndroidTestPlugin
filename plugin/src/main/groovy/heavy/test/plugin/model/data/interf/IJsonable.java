package heavy.test.plugin.model.data.interf;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/24.
 */

public interface IJsonable {

    void parseJsonObject(JSONObject object);

    JSONObject getJsonObject();
}
