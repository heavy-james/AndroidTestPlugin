package heavy.test.plugin.model.wrapper;

import java.util.ArrayList;
import java.util.List;

import heavy.test.plugin.model.data.Extra;

/**
 * Created by heavy on 2017/5/20.
 */

public class ExtraWrapper {

    List<Extra> mExtras;

    public ExtraWrapper() {
        mExtras = new ArrayList<>();
    }

    void add(String key, Object data) {
        Extra extra = new Extra(key, data);
        mExtras.add(extra);
    }

    public List<Extra> getExtras() {
        return mExtras;
    }
}
