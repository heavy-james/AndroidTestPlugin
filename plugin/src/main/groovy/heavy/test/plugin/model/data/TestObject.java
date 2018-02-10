package heavy.test.plugin.model.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by heavy on 2017/6/10.
 */

public class TestObject {

    static Gson mGson = new Gson();

    /**
     * the type of this object, used to rebuild object from json data
     */
    @SerializedName("test_object_class")
    protected String objectType;
    @SerializedName("description")
    protected String description;
    /**
     * a test object is a also a container, this is the test objects hold by this container
     */
    @SerializedName("content_objects")
    List<TestObject> contentObjects;
    @SerializedName("repeat")
    int repeatCount = 1;
    @SerializedName("run_as_condition")
    private boolean runAsCondition;

    public TestObject() {
        contentObjects = new ArrayList<>();
        objectType = getClass().getName();
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public TestObject setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public TestObject clean() {
        contentObjects.clear();
        return this;
    }

    public String getObjectType() {
        return getClass().getName();
    }

    public List<TestObject> getContentObjects() {
        return contentObjects;
    }

    public void addContentObject(TestObject testObject) {
        contentObjects.add(testObject);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRunAsCondition() {
        return runAsCondition;
    }

    public TestObject setRunAsCondition(boolean runAsCondition) {
        this.runAsCondition = runAsCondition;
        return this;
    }

    public JSONObject getJsonObject() {
        return new JSONObject(mGson.toJson(this));
    }
}
