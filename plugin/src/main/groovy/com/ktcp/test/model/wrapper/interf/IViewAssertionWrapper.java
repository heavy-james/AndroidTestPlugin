package com.ktcp.test.model.wrapper.interf;

import com.ktcp.test.model.data.Assertion;

/**
 * Created by heavy on 2017/5/31.
 */

public interface IViewAssertionWrapper {

    Assertion isDisplayed(boolean display);

    Assertion withText(String content);

    Assertion isFullScreen(boolean full);

    Assertion hasFocus(boolean hasFocues);
}
