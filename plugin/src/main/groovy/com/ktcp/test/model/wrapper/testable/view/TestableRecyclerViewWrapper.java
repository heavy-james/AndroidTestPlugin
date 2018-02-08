package com.ktcp.test.model.wrapper.testable.view;


import com.ktcp.test.model.data.testable.view.TestableRecyclerView;
import com.ktcp.test.model.data.testable.view.TestableView;

import groovy.lang.Closure;

/**
 * Created by heavy on 2017/5/20.
 */

public class TestableRecyclerViewWrapper extends TestableViewWrapper {

    public TestableRecyclerViewWrapper(TestableRecyclerView data) {
        super(data);
    }

    void withChild(Closure closure) {
        TestableViewWrapper wrapper = new TestableViewWrapper(new TestableView());
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.setDelegate(wrapper);
        closure.call();
        ((TestableRecyclerView) mTestableView).getChildView().add(wrapper.getTestableView());
    }

}
