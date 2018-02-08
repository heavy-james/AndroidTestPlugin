package heavy.test.plugin.model.wrapper.testable.view;


import heavy.test.plugin.model.data.testable.view.TestableAdapterView;
import heavy.test.plugin.model.data.testable.view.TestableView;

import groovy.lang.Closure;

/**
 * Created by heavy on 2017/5/20.
 */

public class TestableAdapterViewWrapper extends TestableViewWrapper {

    public TestableAdapterViewWrapper(TestableAdapterView data) {
        super(data);
    }

    void className(String className) {
        ((TestableAdapterView) (mTestableView)).setClassName(className);
    }

    void withChild(Closure closure) {
        TestableViewWrapper wrapper = new TestableViewWrapper(new TestableView());
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.setDelegate(wrapper);
        closure.call();
        ((TestableAdapterView) mTestableView).getChildView().add(wrapper.getTestableView());
    }

}
