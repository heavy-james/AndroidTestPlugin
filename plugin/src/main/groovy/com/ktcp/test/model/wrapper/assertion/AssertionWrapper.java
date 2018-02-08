package com.ktcp.test.model.wrapper.assertion;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.assertion.data.Contains;
import com.ktcp.test.model.data.assertion.data.Empty;
import com.ktcp.test.model.data.assertion.data.EndWith;
import com.ktcp.test.model.data.assertion.data.Equals;
import com.ktcp.test.model.data.assertion.data.NullCheck;
import com.ktcp.test.model.data.assertion.data.StartWith;
import com.ktcp.test.model.data.assertion.view.Display;
import com.ktcp.test.model.data.assertion.view.FullScreen;
import com.ktcp.test.model.data.assertion.view.HasFocus;
import com.ktcp.test.model.data.assertion.view.WithText;
import com.ktcp.test.model.data.interf.ITestObject;
import com.ktcp.test.model.wrapper.interf.IDataAssertionWrapper;
import com.ktcp.test.model.wrapper.interf.IViewAssertionWrapper;

/**
 * Created by heavy on 2017/5/31.
 */

public class AssertionWrapper implements IViewAssertionWrapper, IDataAssertionWrapper {

    private ITestObject mAssertions;

    public AssertionWrapper(ITestObject testObject) {
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
