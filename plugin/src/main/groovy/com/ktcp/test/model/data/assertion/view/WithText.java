package com.ktcp.test.model.data.assertion.view;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.factory.AssertionFactory;

/**
 * Created by heavy on 2017/5/20.
 */

public class WithText extends Assertion {

    public WithText(String content) {
        description = content;
    }

    @Override
    public String getAssertionType() {
        return AssertionFactory.VIEW_ASSERTION_WITH_TEXT;
    }

}
