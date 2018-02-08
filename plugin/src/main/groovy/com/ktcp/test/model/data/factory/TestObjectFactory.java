package com.ktcp.test.model.data.factory;

import com.ktcp.test.model.data.TestBlock;
import com.ktcp.test.model.data.TestObject;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/11.
 */

public class TestObjectFactory {

    public static final String OBJECT_TYPE_TESTABLE = "object_type_testable";
    public static final String OBJECT_TYPE_ATOM = "object_type_atom";
    public static final String OBJECT_TYPE_BLOCK = "object_type_block";


    public static TestObject createTestObject(JSONObject object) {

        if (object == null) {
            return null;
        }
        if (OBJECT_TYPE_TESTABLE.equals(object.optString(TestObject.OBJECT_TYPE))) {
            return TestableFactory.createTestable(object);
        }
        if (OBJECT_TYPE_ATOM.equals(object.optString(TestObject.OBJECT_TYPE))) {
            return AtomFactory.createAtom(object);
        }
        if (OBJECT_TYPE_BLOCK.equals(object.optString(TestObject.OBJECT_TYPE))) {
            TestObject testObject = new TestBlock();
            testObject.parseJsonObject(object);
            return testObject;
        }
        throw new IllegalArgumentException("unknown test object type : " + object.toString());
    }

}
