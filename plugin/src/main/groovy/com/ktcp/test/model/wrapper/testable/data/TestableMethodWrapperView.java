package com.ktcp.test.model.wrapper.testable.data;

import com.ktcp.test.model.data.Assertion;
import com.ktcp.test.model.data.testable.data.TestableMethod;
import com.ktcp.test.model.wrapper.assertion.AssertionWrapper;
import com.ktcp.test.model.wrapper.interf.IDataAssertionWrapper;
import com.ktcp.test.model.wrapper.reflection.MethodDataWrapper;

/**
 * Created by heavy on 2017/6/2.
 */

public class TestableMethodWrapperView extends MethodDataWrapper implements IDataAssertionWrapper {

    TestableMethod testableMethod;

    public TestableMethodWrapperView(TestableMethod testableMethod) {
        super(testableMethod.getMethodData());
        this.testableMethod = testableMethod;
    }

    void methodData(String methodDataName) {
        testableMethod.setMethodDataName(methodDataName);
    }

    @Override
    public Assertion assertNull() {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertNull());
        return null;
    }

    @Override
    public Assertion assertNotNull() {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertNotNull());
        return null;
    }

    @Override
    public Assertion assertEquals(Object value) {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertEquals(value));
        return null;
    }

    @Override
    public Assertion assertNotEquals(Object value) {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertNotEquals(value));
        return null;
    }

    @Override
    public Assertion assertContains(String text) {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertContains(text));
        return null;
    }

    @Override
    public Assertion assertNotContains(String text) {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertNotContains(text));
        return null;
    }

    @Override
    public Assertion assertStartWith(String text) {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertStartWith(text));
        return null;
    }

    @Override
    public Assertion assertEndWith(String text) {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertEndWith(text));
        return null;
    }

    @Override
    public Assertion assertEmpty() {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertEmpty());
        return null;
    }

    @Override
    public Assertion assertNotEmpty() {
        testableMethod.getMethodAtoms().add(new AssertionWrapper(null).assertNotEmpty());
        return null;
    }

    public TestableMethod getTestableMethod() {
        return testableMethod;
    }
}
