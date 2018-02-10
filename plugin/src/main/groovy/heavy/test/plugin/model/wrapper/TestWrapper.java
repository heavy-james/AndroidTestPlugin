package heavy.test.plugin.model.wrapper;

import groovy.lang.Closure;
import heavy.test.plugin.logic.TestManager;
import heavy.test.plugin.model.data.Action;
import heavy.test.plugin.model.data.TestContext;
import heavy.test.plugin.model.data.TestObject;
import heavy.test.plugin.model.data.Testable;
import heavy.test.plugin.model.data.testable.global.ConditionedTestable;
import heavy.test.plugin.model.data.testable.global.SetUpActivity;
import heavy.test.plugin.model.wrapper.testable.TestableWrapper;
import heavy.test.plugin.util.LogUtil;

/**
 * Created by heavy on 2017/6/20.
 */

public class TestWrapper extends TestableWrapper {


    public TestWrapper(TestContext testContext) {
        super(testContext, null);
    }

    public TestWrapper() {
        super(new TestContext(), null);
    }

    public void launchActivity(Closure closure) {
        IntentWrapper intentWrapper = new IntentWrapper();
        closure.setDelegate(intentWrapper);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        SetUpActivity setUpActivity = new SetUpActivity(intentWrapper.getIntent());
        LogUtil.d(TAG, "launchActivity : " + setUpActivity.getIntentData().getClassName());
        TestManager.getInstance().sendForResult(setUpActivity);
    }

    public void test(TestObject testObject) {
        TestManager.getInstance().runTestObject(testObject);
    }

    @Override
    public Testable withView(Closure closure) {
        TestManager.getInstance().runTestObject(super.withView(closure));
        return null;
    }

    @Override
    public Testable withAdapterView(Closure closure) {
        TestManager.getInstance().runTestObject(super.withAdapterView(closure));
        return null;
    }

    @Override
    public Testable withRecyclerView(Closure closure) {
        TestManager.getInstance().runTestObject(super.withRecyclerView(closure));
        return null;
    }

    @Override
    public Testable getAdapterView(String name, Closure closure) {
        TestManager.getInstance().runTestObject(super.getAdapterView(name, closure));
        return null;
    }

    @Override
    public Testable getRecyclerView(String name, Closure closure) {
        TestManager.getInstance().runTestObject(super.getRecyclerView(name, closure));
        return null;
    }

    @Override
    public Testable getView(String name, Closure closure) {
        TestManager.getInstance().runTestObject(super.getView(name, closure));
        return null;
    }

    @Override
    public Testable makeToast(String msg, long time) {
        TestManager.getInstance().runTestObject(super.makeToast(msg, time));
        return null;
    }

    @Override
    public Testable makeToast(String msg) {
        TestManager.getInstance().runTestObject(super.makeToast(msg));
        return null;
    }

    @Override
    public Testable printMessage(String msg) {
        TestManager.getInstance().runTestObject(super.printMessage(msg));
        return null;
    }

    @Override
    public Testable stopTest(String msg) {
        TestManager.getInstance().runTestObject(super.stopTest(msg));
        return null;
    }

    @Override
    public Testable throwException(String msg) {
        TestManager.getInstance().runTestObject(super.throwException(msg));
        return null;
    }

    @Override
    public Testable delay(long time) {
        TestManager.getInstance().runTestObject(super.delay(time));
        return null;
    }

    @Override
    public Testable withField(Closure closure) {
        TestManager.getInstance().runTestObject(super.withField(closure));
        return null;
    }

    @Override
    public Testable withMethod(Closure closure) {
        TestManager.getInstance().runTestObject(super.withMethod(closure));
        return null;
    }

    @Override
    public Testable getField(String name, Closure closure) {
        TestManager.getInstance().runTestObject(super.getField(name, closure));
        return null;
    }

    @Override
    public Testable getMethod(String name, Closure closure) {
        TestManager.getInstance().runTestObject(super.getMethod(name, closure));
        return null;
    }

    @Override
    public Testable doIf(Closure closure) {
        TestManager.getInstance().runConditionTestable((ConditionedTestable) super.doIf(closure));
        return null;

    }

    @Override
    public Testable doIfNot(Closure closure) {
        TestManager.getInstance().runConditionTestable((ConditionedTestable) super.doIfNot(closure));
        return null;
    }

    @Override
    public Testable waitUntil(Closure closure) {
        TestManager.getInstance().runConditionTestable((ConditionedTestable) super.waitUntil(closure));
        return null;
    }

    @Override
    public TestObject block(Closure closure) {
        TestObject testObject = super.block(closure);
        TestManager.getInstance().runTestObjects(testObject.getRepeatCount(), testObject.getContentObjects());
        return null;
    }

    @Override
    public Action keyEvent(int keyCode, String eventAction) {
        return super.keyEvent(keyCode, eventAction);
    }

    @Override
    public Action pressKey(int keyCode) {
        TestManager.getInstance().runTestObject(super.pressKey(keyCode));
        return null;
    }

    @Override
    public Action pressBack() {
        TestManager.getInstance().runTestObject(super.pressBack());
        return null;
    }

    @Override
    public Action pressMenu() {
        TestManager.getInstance().runTestObject(super.pressMenu());
        return null;
    }

    @Override
    public Action pressDpadCenter() {
        TestManager.getInstance().runTestObject(super.pressDpadCenter());
        return null;
    }

    @Override
    public Action pressDpadUp() {
        TestManager.getInstance().runTestObject(super.pressDpadUp());
        return null;
    }

    @Override
    public Action pressDpadDown() {
        TestManager.getInstance().runTestObject(super.pressDpadDown());
        return null;
    }

    @Override
    public Action pressDpadLeft() {
        TestManager.getInstance().runTestObject(super.pressDpadLeft());
        return null;
    }

    @Override
    public Action pressDpadRight() {
        TestManager.getInstance().runTestObject(super.pressDpadRight());
        return null;
    }

    @Override
    public Action pressVolumeDown() {
        TestManager.getInstance().runTestObject(super.pressVolumeDown());
        return null;
    }

    @Override
    public Action pressHome() {
        TestManager.getInstance().runTestObject(super.pressHome());
        return null;
    }

    @Override
    public Action pressPower() {
        TestManager.getInstance().runTestObject(super.pressPower());
        return null;
    }

    @Override
    public Action pressVolumeUp() {
        TestManager.getInstance().runTestObject(super.pressVolumeUp());
        return null;
    }
}
