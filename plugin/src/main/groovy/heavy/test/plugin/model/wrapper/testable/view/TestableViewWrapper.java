package heavy.test.plugin.model.wrapper.testable.view;

import groovy.lang.Closure;
import heavy.test.plugin.model.data.Action;
import heavy.test.plugin.model.data.Assertion;
import heavy.test.plugin.model.data.testable.view.TestableView;
import heavy.test.plugin.model.wrapper.action.ActionWrapper;
import heavy.test.plugin.model.wrapper.assertion.AssertionWrapper;
import heavy.test.plugin.model.wrapper.interf.IViewAssertionWrapper;

/**
 * Created by heavy on 2017/5/20.
 */

public class TestableViewWrapper extends ActionWrapper implements IViewAssertionWrapper {

    TestableView mTestableView;

    public TestableViewWrapper(TestableView testableView) {
        super(testableView);
        mTestableView = testableView;
    }

    public void position(int position) {
        mTestableView.setPosition(position);
    }

    public void id(String id) {
        mTestableView.getIdentifier().setId(id);
    }

    public void description(String description) {
        mTestableView.getIdentifier().setDescription(description);
    }

    public void withParent(Closure closure) {
        TestableViewWrapper identifierWrapper = new TestableViewWrapper(new TestableView());
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(identifierWrapper);
        closure.call();
        mTestableView.getIdentifier().setParentIdentifier(identifierWrapper.getTestableView().getIdentifier());
    }

    void perform(Action action) {
        //action is already added when the action is create by ActionWrapper,this method just provides semantic recognition.
    }

    public void perform(Closure closure) {
        ActionWrapper wrapper = new ActionWrapper(mTestableView);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
        //actions added when created by ActionWrapper,just call closure here
    }

    public void check(Closure closure) {
        AssertionWrapper wrapper = new AssertionWrapper(mTestableView);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.setDelegate(wrapper);
        closure.call();
    }

    void check(Assertion assertion) {
        mTestableView.addContentObject(assertion);
    }

    public TestableView getTestableView() {
        return mTestableView;
    }

    @Override
    public Assertion isDisplayed(boolean display) {
        return new AssertionWrapper(null).isDisplayed(display);
    }

    @Override
    public Assertion withText(String content) {
        return new AssertionWrapper(null).withText(content);
    }

    @Override
    public Assertion isFullScreen(boolean full) {
        return new AssertionWrapper(null).isFullScreen(full);
    }

    @Override
    public Assertion hasFocus(boolean hasFocues) {
        return new AssertionWrapper(null).hasFocus(hasFocues);
    }
}
