package heavy.test.plugin.model.wrapper.testable.data;

import heavy.test.plugin.model.data.Assertion;
import heavy.test.plugin.model.data.testable.data.TestableField;
import heavy.test.plugin.model.wrapper.assertion.AssertionWrapper;
import heavy.test.plugin.model.wrapper.interf.IDataAssertionWrapper;
import heavy.test.plugin.model.wrapper.reflection.ObjectDataWrapper;

/**
 * Created by heavy on 2017/6/2.
 */

public class TestableFieldWrapperView extends ObjectDataWrapper implements IDataAssertionWrapper {

    TestableField testableField;

    public TestableFieldWrapperView(TestableField testableField) {
        super(testableField.getFieldData());
        this.testableField = testableField;
    }

    void fieldData(String fieldDataName) {
        testableField.setFieldDataName(fieldDataName);
    }

    @Override
    public Assertion assertNull() {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertNull());
        return null;
    }

    @Override
    public Assertion assertNotNull() {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertNotNull());
        return null;
    }

    @Override
    public Assertion assertEquals(Object value) {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertEquals(value));
        return null;
    }

    @Override
    public Assertion assertNotEquals(Object value) {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertNotEquals(value));
        return null;
    }

    @Override
    public Assertion assertContains(String text) {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertContains(text));
        return null;
    }

    @Override
    public Assertion assertNotContains(String text) {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertNotContains(text));
        return null;
    }

    @Override
    public Assertion assertStartWith(String text) {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertStartWith(text));
        return null;
    }

    @Override
    public Assertion assertEndWith(String text) {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertEndWith(text));
        return null;
    }

    @Override
    public Assertion assertEmpty() {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertEmpty());
        return null;
    }

    @Override
    public Assertion assertNotEmpty() {
        testableField.getFieldAtoms().add(new AssertionWrapper(null).assertNotEmpty());
        return null;
    }

    public TestableField getTestableField() {
        return testableField;
    }
}
