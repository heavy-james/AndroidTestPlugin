package com.ktcp.test.model.data.factory;

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

import org.json.JSONObject;

/**
 * Created by heavy on 2017/5/31.
 */

public class AssertionFactory {

    public static final String TAG = "AssertionFactory";
    public static final String VIEW_ASSERTION_DISPLAY = "isDisplayed";
    public static final String VIEW_ASSERTION_WITH_TEXT = "withText";
    public static final String VIEW_ASSERTION_IS_FULL_SCREEN = "isFullScreen";
    public static final String VIEW_ASSERTION_HAS_FOCUS = "hasFocus";
    public static final String DATA_ASSERTION_NULL_CHECK = "nullCheck";
    public static final String DATA_ASSERTION_START_WITH = "startWith";
    public static final String DATA_ASSERTION_END_WITH = "endWith";
    public static final String DATA_ASSERTION_CONTAINS = "contains";
    public static final String DATA_ASSERTION_EQUALS = "equals";
    public static final String DATA_ASSERTION_EMPTY = "empty";

    public static Assertion createAssertion(JSONObject object) {
        if (object == null) {
            return null;
        }
        Assertion assertion = createAssertion(object.optString("assertion_type"));
        if (assertion != null) {
            assertion.parseJsonObject(object);
            return assertion;
        }
        return null;
    }

    public static Assertion createAssertion(String name) {
        if (VIEW_ASSERTION_DISPLAY.equals(name)) {
            return new Display(false);
        }
        if (VIEW_ASSERTION_WITH_TEXT.equals(name)) {
            return new WithText(null);
        }
        if (VIEW_ASSERTION_IS_FULL_SCREEN.equals(name)) {
            return new FullScreen(false);
        }
        if (VIEW_ASSERTION_HAS_FOCUS.equals(name)) {
            return new HasFocus(false);
        }
        if (DATA_ASSERTION_NULL_CHECK.equals(name)) {
            return new NullCheck(false);
        }
        if (DATA_ASSERTION_START_WITH.equals(name)) {
            return new StartWith(null);
        }
        if (DATA_ASSERTION_END_WITH.equals(name)) {
            return new EndWith(null);
        }
        if (DATA_ASSERTION_CONTAINS.equals(name)) {
            return new Contains(null);
        }
        if (DATA_ASSERTION_EQUALS.equals(name)) {
            return new Equals(null);
        }
        if (DATA_ASSERTION_EMPTY.equals(name)) {
            return new Empty(true);
        }
        throw new IllegalArgumentException("createAssertion null, unknown assertion name : " + name);
    }
}
