package com.ktcp.test.model.data.assertion.data;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.factory.AssertionFactory;

/**
 * Created by heavy on 2017/6/2.
 */

public class EndWith extends Assertion {

    public EndWith(String content) {
        description = content;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.DATA_ASSERTION_END_WITH;
    }

    public String getContent() {
        return description;
    }
}
