package com.ktcp.test.model.wrapper;

import com.ktcp.test.model.data.TestContext;
import com.ktcp.test.model.wrapper.testable.TestableWrapper;

/**
 * Created by heavy on 17/6/27.
 */

public class ContextWrapper extends TestableWrapper {

    private TestContext testContext;

    public ContextWrapper(TestContext testContext) {
        super(testContext, null);
        this.testContext = testContext;
    }

    public void name(String name) {
        testContext.setContextName(name);
    }


    public TestContext getTestContext() {
        return testContext;
    }

}
