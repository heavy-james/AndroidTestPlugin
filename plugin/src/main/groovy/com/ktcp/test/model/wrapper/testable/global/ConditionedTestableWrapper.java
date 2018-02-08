package com.ktcp.test.model.wrapper.testable.global;

import com.ktcp.test.model.data.TestContext;
import com.ktcp.test.model.data.testable.global.ConditionedTestable;
import com.ktcp.test.model.wrapper.TestObjectWrapper;

import groovy.lang.Closure;

/**
 * Created by heavy on 17/6/28.
 */

public class ConditionedTestableWrapper extends TestObjectWrapper {

    public static final String TAG = "ConditionedTestableWrapper";

    protected ConditionedTestable mConditionedTestable;
    protected TestContext testContext;

    public ConditionedTestableWrapper(TestContext testContext, ConditionedTestable conditionedTestable) {
        super(testContext, conditionedTestable);
        this.mConditionedTestable = conditionedTestable;
        this.testContext = testContext;
    }

    void condition(Closure closure) {
        mConditionedTestable.setConditionClosure(closure);
    }

    public void beforeCheck(Closure closure) {
        mConditionedTestable.setPreconditionClosure(closure);
    }

    void caseTrue(Closure closure) {
        mConditionedTestable.setTrueCaseClosure(closure);
    }

    void caseFalse(Closure closure) {
        mConditionedTestable.setFalseCaseClosure(closure);
    }

    void whenTimeOut(Closure closure) {
        mConditionedTestable.setFalseCaseClosure(closure);
    }

    void timeOut(long timeOut) {
        mConditionedTestable.setTimeOut(timeOut);
    }

    void conversed(boolean conversed) {
        mConditionedTestable.setConversed(conversed);
    }

    public ConditionedTestable getConditionedTestable() {
        return mConditionedTestable;
    }

}
