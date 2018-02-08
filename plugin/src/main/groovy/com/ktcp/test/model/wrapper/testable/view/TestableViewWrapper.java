package com.ktcp.test.model.wrapper.testable.view;

import com.ktcp.test.model.data.Action;
import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.factory.TestableViewFactory;
import com.ktcp.test.model.data.testable.view.TestableView;
import com.ktcp.test.model.wrapper.action.ActionWrapper;
import com.ktcp.test.model.wrapper.assertion.AssertionWrapper;
import com.ktcp.test.model.wrapper.interf.IViewAssertionWrapper;

import groovy.lang.Closure;

/**
 * Created by heavy on 2017/5/20.
 */

public class TestableViewWrapper extends ActionWrapper implements IViewAssertionWrapper {

    protected String mType = TestableViewFactory.TYPE_VIEW;

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
