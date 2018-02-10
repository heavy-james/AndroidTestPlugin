package heavy.test.plugin.model.data;

import java.util.HashMap;
import java.util.Map;

import heavy.test.plugin.model.data.reflection.MethodData;
import heavy.test.plugin.model.data.reflection.ObjectData;
import heavy.test.plugin.model.data.testable.data.TestableField;
import heavy.test.plugin.model.data.testable.data.TestableMethod;
import heavy.test.plugin.util.TextUtil;

/**
 * Created by heavy on 2017/5/24.
 */

public class TestContext {

    public static final String TAG = "TestContext";
    TestContext parentTestContext;
    Map<String, TestObject> testObjectMap;
    private String contextName;

    public TestContext() {
        testObjectMap = new HashMap<>();
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    public boolean isEmpty() {
        boolean result = testObjectMap.isEmpty();
        if (parentTestContext != null) {
            result &= parentTestContext.isEmpty();
        }
        return result;
    }

    public void addTestObject(String name, TestObject testObject) {
        if (!TextUtil.isEmpty(name) && testObject != null) {
            testObjectMap.put(name, testObject);
        }
    }

    public TestObject getTestObject(String name) {
        TestObject result = testObjectMap.get(name);
        if (result == null && parentTestContext != null) {
            result = parentTestContext.getTestObject(name);
        }
        return result;
    }

    public TestContext getParentTestContext() {
        return parentTestContext;
    }

    public boolean setParentTestContext(TestContext context) {
        if (this == context || parentTestContext == context) {
            return false;
        }
        if (parentTestContext != null) {
            return parentTestContext.setParentTestContext(context);
        }
        parentTestContext = context;
        return true;
    }

    public MethodData getMethodData(String testableMethodName) {
        TestObject testObject = getTestObject(testableMethodName);
        if (testObject instanceof TestableMethod) {
            return ((TestableMethod) testObject).getMethodData();
        }
        throw new IllegalArgumentException("no object data found, name :" + testableMethodName + " ,context : " + this);
    }

    public ObjectData getObjectData(String testableFieldName) {
        TestObject testObject = getTestObject(testableFieldName);
        if (testObject instanceof TestableField) {
            return ((TestableField) testObject).getFieldData();
        }
        throw new IllegalArgumentException("no object data found, name :" + testableFieldName + " ,context : " + this);
    }
}
