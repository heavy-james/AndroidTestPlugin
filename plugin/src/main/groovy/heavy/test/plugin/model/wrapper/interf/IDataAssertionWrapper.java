package heavy.test.plugin.model.wrapper.interf;

import heavy.test.plugin.model.data.Assertion;

/**
 * Created by heavy on 2017/6/2.
 */

public interface IDataAssertionWrapper {
    Assertion assertNull();

    Assertion assertNotNull();

    Assertion assertEquals(Object value);

    Assertion assertNotEquals(Object value);

    Assertion assertContains(String text);

    Assertion assertNotContains(String text);

    Assertion assertStartWith(String text);

    Assertion assertEndWith(String text);

    Assertion assertEmpty();

    Assertion assertNotEmpty();
}
