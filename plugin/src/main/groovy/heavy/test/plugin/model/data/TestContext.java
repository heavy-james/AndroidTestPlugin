package heavy.test.plugin.model.data;

import heavy.test.plugin.model.data.interf.IJsonable;
import heavy.test.plugin.model.data.interf.ITestObject;
import heavy.test.plugin.model.data.reflection.ObjectData;
import heavy.test.plugin.model.data.testable.data.TestableMethod;
import heavy.test.plugin.util.TextUtil;
import heavy.test.plugin.model.data.factory.TestObjectFactory;
import heavy.test.plugin.model.data.reflection.MethodData;
import heavy.test.plugin.model.data.testable.data.TestableField;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by heavy on 2017/5/24.
 */

public class TestContext implements IJsonable {

    public static final String TAG = "TestContext";
    TestContext parentTestContext;
    Map<String, ITestObject> testObjectMap;
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

    public void addTestObject(String name, ITestObject testObject) {
        if (!TextUtil.isEmpty(name) && testObject != null) {
            testObjectMap.put(name, testObject);
        }
    }

    public ITestObject getTestObject(String name) {
        ITestObject result = testObjectMap.get(name);
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
        ITestObject testObject = getTestObject(testableMethodName);
        if (testObject instanceof TestableMethod) {
            return ((TestableMethod) testObject).getMethodData();
        }
        throw new IllegalArgumentException("no object data found, name :" + testableMethodName + " ,context : " + getJsonObject().toString());
    }

    public ObjectData getObjectData(String testableFieldName) {
        ITestObject testObject = getTestObject(testableFieldName);
        if (testObject instanceof TestableField) {
            return ((TestableField) testObject).getFieldData();
        }
        throw new IllegalArgumentException("no object data found, name :" + testableFieldName + " ,context : " + getJsonObject().toString());
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        if (object == null) {
            return;
        }
        contextName = object.optString("context_name");
        JSONObject testObjects = object.optJSONObject("test_objects");
        if (null != testObjects) {
            for (String key : testObjects.keySet()) {
                TestObject testObject = TestObjectFactory.createTestObject(testObjects.getJSONObject(key));
                testObjectMap.put(key, testObject);
            }
        }

        JSONObject parentData = object.optJSONObject("parent_context");
        if (parentData != null) {
            if (parentTestContext == null) {
                parentTestContext = new TestContext();
            }
            parentTestContext.parseJsonObject(parentData);
        }

    }

    @Override
    public JSONObject getJsonObject() {

        JSONObject result = new JSONObject();
        if (!TextUtil.isEmpty(contextName)) {
            result.put("context_name", contextName);
        }
        if (testObjectMap != null && testObjectMap.size() > 0) {
            JSONObject testObjects = new JSONObject();
            Set<String> keys = testObjectMap.keySet();
            for (String key : keys) {
                if (testObjectMap.get(key) != null) {
                    testObjects.put(key, testObjectMap.get(key).getJsonObject());
                }
            }
            result.put("test_objects", testObjects);
        }
        if (parentTestContext != null) {
            result.put("parent_context", parentTestContext.getJsonObject());
        }
        return result;
    }
}
