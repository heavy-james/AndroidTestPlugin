package heavy.test.plugin.model.wrapper.interf;

import heavy.test.plugin.model.data.Assertion;

/**
 * Created by heavy on 2017/5/31.
 */

public interface IViewAssertionWrapper {

    Assertion isDisplayed(boolean display);

    Assertion withText(String content);

    Assertion isFullScreen(boolean full);

    Assertion hasFocus(boolean hasFocues);
}
