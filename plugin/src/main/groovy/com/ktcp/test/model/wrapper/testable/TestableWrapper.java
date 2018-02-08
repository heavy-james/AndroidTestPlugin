package com.ktcp.test.model.wrapper.testable;

import com.ktcp.test.model.data.Action;
import com.ktcp.test.model.data.TestBlock;
import com.ktcp.test.model.data.TestContext;
import com.ktcp.test.model.data.Testable;
import com.ktcp.test.model.data.interf.ITestObject;
import com.ktcp.test.model.data.testable.global.ConditionedTestable;
import com.ktcp.test.model.data.testable.global.TestableDelay;
import com.ktcp.test.model.data.testable.global.TestableException;
import com.ktcp.test.model.data.testable.global.TestableMessage;
import com.ktcp.test.model.data.testable.global.TestableStop;
import com.ktcp.test.model.data.testable.global.TestableToast;
import com.ktcp.test.model.data.testable.view.TestableAdapterView;
import com.ktcp.test.model.data.testable.view.TestableRecyclerView;
import com.ktcp.test.model.data.testable.view.TestableView;
import com.ktcp.test.model.wrapper.ContextWrapper;
import com.ktcp.test.model.wrapper.TestObjectWrapper;
import com.ktcp.test.model.wrapper.action.ActionWrapper;
import com.ktcp.test.model.wrapper.interf.IKeyEventWrapper;
import com.ktcp.test.model.wrapper.interf.ITestableWrapper;
import com.ktcp.test.model.wrapper.testable.global.ConditionedTestableWrapper;
import com.ktcp.test.model.wrapper.testable.view.TestableAdapterViewWrapper;
import com.ktcp.test.model.wrapper.testable.view.TestableRecyclerViewWrapper;
import com.ktcp.test.model.wrapper.testable.view.TestableViewWrapper;
import com.ktcp.test.util.TextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import groovy.lang.Closure;

/**
 * Created by heavy on 2017/5/31.
 */

public class TestableWrapper extends TestObjectWrapper implements ITestableWrapper, IKeyEventWrapper {

    public static final String TAG = "TestableWrapper";

    private static List<TestContext> savedTestContexts = new ArrayList<>();
    private static Map<String, ITestObject> cachedVars = new HashMap<>();


    public TestableWrapper(TestContext testContext, ITestObject testObject) {
        super(testContext, testObject);
    }

    public static void defVar(String name, ITestObject testObject) {
        cachedVars.put(name, testObject);
    }

    public void context(Closure closure) {
        ContextWrapper contextWrapper = new ContextWrapper(new TestContext());
        closure.setDelegate(contextWrapper);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        savedTestContexts.add(contextWrapper.getTestContext());
    }

    public void useContext(String name) {
        for (TestContext testContext : savedTestContexts) {
            if (TextUtil.equals(testContext.getContextName(), name)) {
                mTestContext.setParentTestContext(testContext);
            }
        }
    }

    public ITestObject useVar(String name) {
        return cachedVars.get(name);
    }


    @Override
    public Testable withView(Closure closure) {
        TestableViewWrapper wrapper = new TestableViewWrapper(new TestableView());
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
        Testable testable = wrapper.getTestableView();
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable withAdapterView(Closure closure) {
        TestableAdapterViewWrapper wrapper = new TestableAdapterViewWrapper(new TestableAdapterView());
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
        Testable testable = wrapper.getTestableView();
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable withRecyclerView(Closure closure) {
        TestableRecyclerViewWrapper wrapper = new TestableRecyclerViewWrapper(new TestableRecyclerView());
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
        Testable testable = wrapper.getTestableView();
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable addView(String name, Closure closure) {
        TestableViewWrapper wrapper = new TestableViewWrapper(new TestableView());
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
        Testable testable = wrapper.getTestableView();
        if (mTestContext != null) {
            mTestContext.addTestObject(name, testable);
        }
        return testable;
    }

    @Override
    public Testable addAdapterView(String name, Closure closure) {
        TestableAdapterViewWrapper wrapper = new TestableAdapterViewWrapper(new TestableAdapterView());
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
        Testable testable = wrapper.getTestableView();
        if (mTestContext != null) {
            mTestContext.addTestObject(name, testable);
        }
        return testable;
    }

    @Override
    public Testable addRecyclerView(String name, Closure closure) {
        TestableRecyclerViewWrapper wrapper = new TestableRecyclerViewWrapper(new TestableRecyclerView());
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
        Testable testable = wrapper.getTestableView();
        if (mTestContext != null) {
            mTestContext.addTestObject(name, testable);
        }
        return testable;
    }

    @Override
    public Testable getAdapterView(String name, Closure closure) {
        ITestObject testObject = mTestContext.getTestObject(name).clean();
        TestableAdapterView result = (TestableAdapterView) testObject;
        closure.setDelegate(new TestableAdapterViewWrapper(result));
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        if (mTestObject != null) {
            mTestObject.addContentObject(result);
        }
        return result;
    }

    @Override
    public Testable getRecyclerView(String name, Closure closure) {
        ITestObject testObject = mTestContext.getTestObject(name).clean();
        TestableRecyclerView result = (TestableRecyclerView) testObject;
        closure.setDelegate(new TestableAdapterViewWrapper(result));
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        if (mTestObject != null) {
            mTestObject.addContentObject(result);
        }
        return result;
    }

    @Override
    public Testable getView(String name, Closure closure) {
        ITestObject testObject = mTestContext.getTestObject(name).clean();
        TestableView result = (TestableView) testObject;
        closure.setDelegate(new TestableViewWrapper(result));
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        if (mTestObject != null) {
            mTestObject.addContentObject(result);
        }
        return result;
    }

    @Override
    public Testable makeToast(String msg, long time) {
        Testable testable = new TestableToast(msg, time);
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable makeToast(String msg) {
        return makeToast(msg, 2000);
    }

    @Override
    public Testable printMessage(String msg) {
        Testable testable = new TestableMessage(msg);
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable stopTest(String msg) {
        Testable testable = new TestableStop(msg);
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable throwException(String msg) {
        Testable testable = new TestableException(msg);
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable delay(long time) {
        Testable testable = new TestableDelay(time);
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    public ITestObject doIf(Closure closure) {
        ConditionedTestableWrapper wrapper = new ConditionedTestableWrapper(mTestContext, new ConditionedTestable(mTestContext));
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
        ITestObject testObject = wrapper.getConditionedTestable();
        if (mTestObject != null) {
            mTestObject.addContentObject(testObject);
        }
        return testObject;
    }

    public ITestObject doIfNot(Closure closure) {
        ConditionedTestable testable = (ConditionedTestable) doIf(closure);
        testable.setConversed(true);
        return testable;
    }

    public ITestObject block(Closure closure) {
        ITestObject testObject = new TestBlock();
        TestableWrapper testableWrapper = new TestableWrapper(mTestContext, testObject);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(testableWrapper);
        closure.call();
        if (mTestObject != null) {
            mTestObject.addContentObject(testObject);
        }
        return testObject;
    }

    public ITestObject waitUntil(Closure closure) {
        return doIf(closure);
    }

    public ITestObject getTestObject() {
        return mTestObject;
    }

    @Override
    public Action keyEvent(int keyCode, String eventAction) {
        return new ActionWrapper(mTestObject).keyEvent(keyCode, eventAction);
    }

    @Override
    public Action pressKey(int keyCode) {
        return new ActionWrapper(mTestObject).pressKey(keyCode);
    }

    @Override
    public Action pressBack() {
        return new ActionWrapper(mTestObject).pressBack();
    }

    @Override
    public Action pressMenu() {
        return new ActionWrapper(mTestObject).pressMenu();
    }

    @Override
    public Action pressDpadCenter() {
        return new ActionWrapper(mTestObject).pressDpadCenter();
    }

    @Override
    public Action pressDpadUp() {
        return new ActionWrapper(mTestObject).pressDpadUp();
    }

    @Override
    public Action pressDpadDown() {
        return new ActionWrapper(mTestObject).pressDpadDown();
    }

    @Override
    public Action pressDpadLeft() {
        return new ActionWrapper(mTestObject).pressDpadLeft();
    }

    @Override
    public Action pressDpadRight() {
        return new ActionWrapper(mTestObject).pressDpadRight();
    }

    @Override
    public Action pressVolumeDown() {
        return new ActionWrapper(mTestObject).pressVolumeDown();
    }

    @Override
    public Action pressHome() {
        return new ActionWrapper(mTestObject).pressHome();
    }

    @Override
    public Action pressPower() {
        return new ActionWrapper(mTestObject).pressPower();
    }

    @Override
    public Action pressVolumeUp() {
        return new ActionWrapper(mTestObject).pressVolumeUp();
    }
}
