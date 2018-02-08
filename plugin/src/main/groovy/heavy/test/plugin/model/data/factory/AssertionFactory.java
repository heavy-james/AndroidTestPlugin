package heavy.test.plugin.model.data.factory;

import heavy.test.plugin.model.data.assertion.data.Equals;
import heavy.test.plugin.model.data.assertion.data.StartWith;
import heavy.test.plugin.model.data.assertion.view.WithText;
import heavy.test.plugin.model.data.Assertion;
import heavy.test.plugin.model.data.assertion.data.Contains;
import heavy.test.plugin.model.data.assertion.data.Empty;
import heavy.test.plugin.model.data.assertion.data.EndWith;
import heavy.test.plugin.model.data.assertion.data.NullCheck;
import heavy.test.plugin.model.data.assertion.view.Display;
import heavy.test.plugin.model.data.assertion.view.FullScreen;
import heavy.test.plugin.model.data.assertion.view.HasFocus;

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
