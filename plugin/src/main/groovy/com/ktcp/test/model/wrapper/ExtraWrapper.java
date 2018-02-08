package com.ktcp.test.model.wrapper;

import com.ktcp.test.model.data.Extra;

import java.util.ArrayList;
import java.util.List;

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
