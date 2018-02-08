package com.ktcp.test.model.data.testable.global;

import com.ktcp.test.model.data.factory.GlobalTestableFactory;

/**
 * Created by heavy on 2017/6/1.
 */

public class TestableStop extends GlobalTestable {

    public TestableStop() {

    }

    public TestableStop(String msg) {
        description = msg;
    }

    @Override
    public String getGlobalTestableType() {
        return GlobalTestableFactory.TYPE_STOP;
    }

    public String getStopMessage() {
        return description;
    }

    public void setStopMessage(String msg) {
        description = msg;
    }
}
