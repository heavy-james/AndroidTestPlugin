package com.ktcp.test.model.wrapper.action;

import com.ktcp.test.model.data.Action;
import com.ktcp.test.model.data.action.global.Delay;
import com.ktcp.test.model.data.action.view.Check;
import com.ktcp.test.model.data.action.view.Click;
import com.ktcp.test.model.data.action.view.Focus;
import com.ktcp.test.model.data.action.view.KeyEvent;
import com.ktcp.test.model.data.action.view.KeyPress;
import com.ktcp.test.model.data.interf.ITestObject;
import com.ktcp.test.model.wrapper.interf.IActionWrapper;
import com.ktcp.test.model.wrapper.interf.IKeyEventWrapper;

/**
 * Created by heavy on 2017/5/20.
 */

public class ActionWrapper implements IActionWrapper, IKeyEventWrapper {

    public static final int KEYCODE_HOME = 3;
    public static final int KEYCODE_BACK = 4;
    public static final int KEYCODE_DPAD_UP = 19;
    public static final int KEYCODE_DPAD_DOWN = 20;
    public static final int KEYCODE_DPAD_LEFT = 21;
    public static final int KEYCODE_DPAD_RIGHT = 22;
    public static final int KEYCODE_DPAD_CENTER = 23;
    public static final int KEYCODE_VOLUME_UP = 24;
    public static final int KEYCODE_VOLUME_DOWN = 25;
    public static final int KEYCODE_POWER = 26;
    public static final int KEYCODE_MENU = 82;

    protected ITestObject mTestObject;

    public ActionWrapper(ITestObject testObject) {
        this.mTestObject = testObject;
    }

    public Action setChecked(boolean check) {
        Action action = new Check(check);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action setFocused(boolean focused) {
        Action action = new Focus(focused);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action click() {
        Action action = new Click();
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action keyEvent(int keyCode, String eventAction) {
        Action action = new KeyEvent(keyCode, eventAction);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressKey(int keyCode) {
        Action action = new KeyPress(keyCode);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressBack() {
        Action action = new KeyPress(KEYCODE_BACK);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressDpadCenter() {
        Action action = new KeyPress(KEYCODE_DPAD_CENTER);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressDpadUp() {
        Action action = new KeyPress(KEYCODE_DPAD_UP);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressDpadDown() {
        Action action = new KeyPress(KEYCODE_DPAD_DOWN);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressDpadLeft() {
        Action action = new KeyPress(KEYCODE_DPAD_LEFT);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressDpadRight() {
        Action action = new KeyPress(KEYCODE_DPAD_RIGHT);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressVolumeDown() {
        Action action = new KeyPress(KEYCODE_VOLUME_DOWN);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressVolumeUp() {
        Action action = new KeyPress(KEYCODE_VOLUME_UP);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressHome() {
        Action action = new KeyPress(KEYCODE_HOME);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressPower() {
        Action action = new KeyPress(KEYCODE_POWER);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action pressMenu() {
        Action action = new KeyPress(KEYCODE_MENU);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public Action delay(long delayMillis) {
        Action action = new Delay(delayMillis);
        if (mTestObject != null) {
            mTestObject.addContentObject(action);
        }
        return action;
    }

    public ITestObject getActions() {
        return mTestObject;
    }
}
