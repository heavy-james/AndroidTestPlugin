package com.ktcp.test.model.wrapper;

import com.ktcp.test.model.data.TestContext;
import com.ktcp.test.model.data.Testable;
import com.ktcp.test.model.data.interf.ITestObject;
import com.ktcp.test.model.data.reflection.MethodData;
import com.ktcp.test.model.data.reflection.ObjectData;
import com.ktcp.test.model.data.reflection.RuntimeValue;
import com.ktcp.test.model.data.testable.data.TestableField;
import com.ktcp.test.model.data.testable.data.TestableMethod;
import com.ktcp.test.model.wrapper.testable.TestableWrapper;
import com.ktcp.test.model.wrapper.testable.data.TestableFieldWrapperView;
import com.ktcp.test.model.wrapper.testable.data.TestableMethodWrapperView;
import com.ktcp.test.util.TextUtil;

import groovy.lang.Closure;

/**
 * Created by heavy on 17/6/29.
 */

public class TestObjectWrapper {

    protected ITestObject mTestObject;
    protected TestContext mTestContext;

    public TestObjectWrapper(TestContext testContext, ITestObject testObject) {
        this.mTestObject = testObject;
        this.mTestContext = testContext;
    }

    public void asVar(String name) {
        TestableWrapper.defVar(name, mTestObject);
    }

    public RuntimeValue useData(String name) {
        RuntimeValue runtimeValue = new RuntimeValue();
        runtimeValue.setObjectData(mTestContext.getObjectData(name));
        runtimeValue.setFromMethod(false);
        resolveObjectData(runtimeValue.getObjectData());
        return runtimeValue.resolve();
    }

    public RuntimeValue callMethod(String name) {
        RuntimeValue runtimeValue = new RuntimeValue();
        runtimeValue.setMethodData(mTestContext.getMethodData(name));
        runtimeValue.setFromMethod(true);
        resolveMethodData(runtimeValue.getMethodData());
        return runtimeValue.resolve();
    }

    public int randomInt(int maxValue) {
        return (int) (Math.random() * maxValue);
    }

    public void repeat(int repeatCount) {
        if (mTestObject != null) {
            mTestObject.setRepeatCount(repeatCount);
        }
    }

    public Testable addField(String name, Closure closure) {
        TestableFieldWrapperView wrapper = new TestableFieldWrapperView(new TestableField());
        closure.setDelegate(wrapper);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        if (mTestContext != null) {
            mTestContext.addTestObject(name, wrapper.getTestableField());
        }
        return wrapper.getTestableField();
    }

    public Testable addMethod(String name, Closure closure) {
        TestableMethodWrapperView wrapper = new TestableMethodWrapperView(new TestableMethod());
        closure.setDelegate(wrapper);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        if (mTestContext != null) {
            mTestContext.addTestObject(name, wrapper.getTestableMethod());
        }
        return wrapper.getTestableMethod();
    }

    public Testable withField(Closure closure) {
        TestableField testableField = new TestableField();
        TestableFieldWrapperView wrapper = new TestableFieldWrapperView(testableField);
        closure.setDelegate(wrapper);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        resolveObjectData(testableField.getFieldData());
        if (mTestObject != null) {
            mTestObject.addContentObject(wrapper.getTestableField());
        }
        return wrapper.getTestableField();
    }

    public Testable withMethod(Closure closure) {
        TestableMethod testableMethod = new TestableMethod();
        TestableMethodWrapperView wrapper = new TestableMethodWrapperView(testableMethod);
        closure.setDelegate(wrapper);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        resolveObjectData(testableMethod.getMethodData().getContainer());
        if (mTestObject != null) {
            mTestObject.addContentObject(wrapper.getTestableMethod());
        }
        return wrapper.getTestableMethod();
    }

    public Testable getField(String name, Closure closure) {
        TestableField field = (TestableField) mTestContext.getTestObject(name).clean();
        TestableFieldWrapperView wrapper = new TestableFieldWrapperView(field);
        closure.setDelegate(wrapper);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        resolveObjectData(field.getFieldData());
        if (mTestObject != null) {
            mTestObject.addContentObject(wrapper.getTestableField());
        }
        return wrapper.getTestableField();
    }

    public Testable getMethod(String name, Closure closure) {
        TestableMethod method = (TestableMethod) mTestContext.getTestObject(name).clean();
        TestableMethodWrapperView wrapper = new TestableMethodWrapperView(method);
        closure.setDelegate(wrapper);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        resolveObjectData(method.getMethodData().getContainer());
        if (mTestObject != null) {
            mTestObject.addContentObject(method);
        }
        return method;
    }

    private void resolveObjectData(ObjectData objectData) {
        if (objectData == null || objectData.getContainer() != null || TextUtil.isEmpty(objectData.getContainerName())
                || TextUtil.equals(objectData.getContainerName(), ObjectData.FROM_ACTIVITY)) {
            return;
        }
        ObjectData containerData = mTestContext.getObjectData(objectData.getContainerName());
        resolveObjectData(containerData);
        objectData.setContainer(containerData);
    }

    private void resolveMethodData(MethodData methodData) {
        if (methodData == null || methodData.getContainer() != null || TextUtil.isEmpty(methodData.getContainerName())
                || TextUtil.equals(methodData.getContainerName(), ObjectData.FROM_ACTIVITY)) {
            return;
        }
        ObjectData containerData = mTestContext.getObjectData(methodData.getContainerName());
        resolveObjectData(containerData);
        methodData.setContainer(containerData);
    }
}
