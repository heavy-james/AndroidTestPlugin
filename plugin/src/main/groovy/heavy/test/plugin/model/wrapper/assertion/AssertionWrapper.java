package heavy.test.plugin.model.wrapper.assertion;

import heavy.test.plugin.model.data.Assertion;
import heavy.test.plugin.model.data.TestObject;
import heavy.test.plugin.model.data.assertion.data.Contains;
import heavy.test.plugin.model.data.assertion.data.Empty;
import heavy.test.plugin.model.data.assertion.data.EndWith;
import heavy.test.plugin.model.data.assertion.data.Equals;
import heavy.test.plugin.model.data.assertion.data.NullCheck;
import heavy.test.plugin.model.data.assertion.data.StartWith;
import heavy.test.plugin.model.data.assertion.view.Display;
import heavy.test.plugin.model.data.assertion.view.FullScreen;
import heavy.test.plugin.model.data.assertion.view.HasFocus;
import heavy.test.plugin.model.data.assertion.view.WithText;
import heavy.test.plugin.model.wrapper.interf.IDataAssertionWrapper;
import heavy.test.plugin.model.wrapper.interf.IViewAssertionWrapper;

/**
 * Created by heavy on 2017/5/31.
 */

public class AssertionWrapper implements IViewAssertionWrapper, IDataAssertionWrapper {

    private TestObject mAssertions;

    public AssertionWrapper(TestObject testObject) {
        mAssertions = testObject;
    }

    @Override
    public Assertion isDisplayed(boolean display) {
        Assertion result = new Display(display);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion withText(String content) {
        Assertion result = new WithText(content);
        if (result != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion isFullScreen(boolean full) {
        Assertion result = new FullScreen(full);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion hasFocus(boolean haFocus) {
        Assertion result = new HasFocus(haFocus);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion assertNull() {
        Assertion result = new NullCheck(true);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion assertNotNull() {
        Assertion result = new NullCheck(false);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion assertEquals(Object value) {
        Assertion result = new Equals(value);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion assertNotEquals(Object value) {
        Assertion result = new Equals(value, true);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion assertContains(String text) {
        Assertion result = new Contains(text);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion assertNotContains(String text) {
        Assertion result = new Contains(text, true);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion assertStartWith(String text) {
        Assertion result = new StartWith(text);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion assertEndWith(String text) {
        Assertion result = new EndWith(text);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }


    @Override
    public Assertion assertEmpty() {
        Assertion result = new Empty(true);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }

    @Override
    public Assertion assertNotEmpty() {
        Assertion result = new Empty(false);
        if (mAssertions != null) {
            mAssertions.addContentObject(result);
        }
        return result;
    }
}
