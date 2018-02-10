package heavy.test.plugin.model.wrapper.testable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import groovy.lang.Closure;
import heavy.test.plugin.model.data.Action;
import heavy.test.plugin.model.data.TestBlock;
import heavy.test.plugin.model.data.TestContext;
import heavy.test.plugin.model.data.TestObject;
import heavy.test.plugin.model.data.Testable;
import heavy.test.plugin.model.data.testable.global.ConditionedTestable;
import heavy.test.plugin.model.data.testable.global.Delay;
import heavy.test.plugin.model.data.testable.global.MakeToast;
import heavy.test.plugin.model.data.testable.global.SendMessage;
import heavy.test.plugin.model.data.testable.global.StopTest;
import heavy.test.plugin.model.data.testable.global.ThrowException;
import heavy.test.plugin.model.data.testable.view.TestableAdapterView;
import heavy.test.plugin.model.data.testable.view.TestableRecyclerView;
import heavy.test.plugin.model.data.testable.view.TestableView;
import heavy.test.plugin.model.wrapper.ContextWrapper;
import heavy.test.plugin.model.wrapper.TestObjectWrapper;
import heavy.test.plugin.model.wrapper.action.ActionWrapper;
import heavy.test.plugin.model.wrapper.interf.IKeyEventWrapper;
import heavy.test.plugin.model.wrapper.interf.ITestableWrapper;
import heavy.test.plugin.model.wrapper.testable.global.ConditionedTestableWrapper;
import heavy.test.plugin.model.wrapper.testable.view.TestableAdapterViewWrapper;
import heavy.test.plugin.model.wrapper.testable.view.TestableRecyclerViewWrapper;
import heavy.test.plugin.model.wrapper.testable.view.TestableViewWrapper;
import heavy.test.plugin.util.TextUtil;

/**
 * Created by heavy on 2017/5/31.
 */

public class TestableWrapper extends TestObjectWrapper implements ITestableWrapper, IKeyEventWrapper {

    public static final String TAG = "TestableWrapper";

    private static List<TestContext> savedTestContexts = new ArrayList<>();
    private static Map<String, TestObject> cachedVars = new HashMap<>();


    public TestableWrapper(TestContext testContext, TestObject testObject) {
        super(testContext, testObject);
    }

    public static void defVar(String name, TestObject testObject) {
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

    public TestObject useVar(String name) {
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
        TestObject testObject = mTestContext.getTestObject(name).clean();
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
        TestObject testObject = mTestContext.getTestObject(name).clean();
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
        TestObject testObject = mTestContext.getTestObject(name).clean();
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
        Testable testable = new MakeToast(msg, time);
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
        Testable testable = new SendMessage(msg);
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable stopTest(String msg) {
        Testable testable = new StopTest(msg);
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable throwException(String msg) {
        Testable testable = new ThrowException(msg);
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    @Override
    public Testable delay(long time) {
        Testable testable = new Delay(time);
        if (mTestObject != null) {
            mTestObject.addContentObject(testable);
        }
        return testable;
    }

    public TestObject doIf(Closure closure) {
        ConditionedTestableWrapper wrapper = new ConditionedTestableWrapper(mTestContext, new ConditionedTestable(mTestContext));
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
        TestObject testObject = wrapper.getConditionedTestable();
        if (mTestObject != null) {
            mTestObject.addContentObject(testObject);
        }
        return testObject;
    }

    public TestObject doIfNot(Closure closure) {
        ConditionedTestable testable = (ConditionedTestable) doIf(closure);
        testable.setConversed(true);
        return testable;
    }

    public TestObject block(Closure closure) {
        TestObject testObject = new TestBlock();
        TestableWrapper testableWrapper = new TestableWrapper(mTestContext, testObject);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(testableWrapper);
        closure.call();
        if (mTestObject != null) {
            mTestObject.addContentObject(testObject);
        }
        return testObject;
    }

    public TestObject waitUntil(Closure closure) {
        return doIf(closure);
    }

    public TestObject getTestObject() {
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
