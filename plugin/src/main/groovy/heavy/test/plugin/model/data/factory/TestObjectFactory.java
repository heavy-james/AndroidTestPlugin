package heavy.test.plugin.model.data.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;

import heavy.test.plugin.model.data.TestObject;
import heavy.test.plugin.util.LogUtil;

/**
 * Created by heavy on 2017/6/11.
 */

public class TestObjectFactory {

    static final String TAG = "TestObjectFactory";

    public static Gson mGson = null;

    static {
        mGson = new GsonBuilder().registerTypeAdapter(TestObject.class, new JsonDeserializer<TestObject>() {
            @Override
            public TestObject deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

                JsonObject object = jsonElement.getAsJsonObject();
                if (object.get("test_object_class") == null) {
                    throw new JsonParseException("can not parson json object class : " + jsonElement.getAsString());
                }

                Class<?> clazz = null;
                try {
                    clazz = Class.forName(object.get("test_object_class").getAsString());
                } catch (ClassNotFoundException e) {
                    throw new JsonParseException("can not find class : " + object.get("test_object_class").getAsString());
                }

                return context.deserialize(jsonElement, clazz);
            }
        }).create();
    }

    public static TestObject createTestObject(JSONObject object) {

        LogUtil.d(TAG, "create createTestObject : " + object);

        if (object == null) {
            return null;
        }
        return mGson.fromJson(object.toString(), TestObject.class);
    }

}
