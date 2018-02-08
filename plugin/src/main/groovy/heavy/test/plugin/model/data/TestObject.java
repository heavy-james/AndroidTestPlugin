package heavy.test.plugin.model.data;

import heavy.test.plugin.model.data.interf.ITestObject;
import heavy.test.plugin.model.data.factory.TestObjectFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heavy on 2017/6/10.
 */

public class TestObject implements ITestObject {

    public static final String OBJECT_TYPE = "object_type";

    /**
     * the type of this object, used to rebuild object from json data
     */
    protected String objectType;

    /**
     * a test object is a also a container, this is the test objects hold by this container
     */
    List<ITestObject> contentObjects;

    int repeatCount = 1;

    public TestObject() {
        contentObjects = new ArrayList<>();
    }

    @Override
    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    @Override
    public ITestObject clean() {
        contentObjects.clear();
        return this;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        objectType = object.optString(OBJECT_TYPE);
        JSONArray contentObjectsArray = object.optJSONArray("content_objects");
        if (contentObjectsArray != null) {
            for (int i = 0; i < contentObjectsArray.length(); i++) {
                ITestObject testObject = TestObjectFactory.createTestObject(contentObjectsArray.optJSONObject(i));
                contentObjects.add(testObject);
            }
        }
        repeatCount = object.optInt("repeat", 1);
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = new JSONObject();
        if (contentObjects != null && contentObjects.size() > 0) {
            JSONArray contentObjectsArray = new JSONArray();
            for (ITestObject testObject : contentObjects) {
                if (testObject != null) {
                    contentObjectsArray.put(testObject.getJsonObject());
                }
            }
            result.put("content_objects", contentObjectsArray);
        }
        if (repeatCount != 1) {
            result.put("repeat", repeatCount);
        }
        result.putOpt(OBJECT_TYPE, objectType);
        return result;
    }

    @Override
    public String getObjectType() {
        return null;
    }

    @Override
    public List<ITestObject> getContentObjects() {
        return contentObjects;
    }

    @Override
    public void addContentObject(ITestObject testObject) {
        contentObjects.add(testObject);
    }


}
