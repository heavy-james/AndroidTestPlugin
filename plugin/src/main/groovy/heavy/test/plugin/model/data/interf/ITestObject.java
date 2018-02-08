package heavy.test.plugin.model.data.interf;

import java.util.List;

/**
 * Created by heavy on 2017/6/9.
 */

public interface ITestObject extends IJsonable, Cloneable {

    /**
     * description of the object type, used to rebuild object from json data
     *
     * @return
     */
    String getObjectType();

    /**
     * return all the other test objects holds with runtime value by this test object
     *
     * @return
     */
    List<ITestObject> getContentObjects();

    /**
     * add an test object into this test object
     *
     * @param runtimeValue
     */
    void addContentObject(ITestObject runtimeValue);

    int getRepeatCount();

    void setRepeatCount(int repeatCount);

    ITestObject clean();
}
