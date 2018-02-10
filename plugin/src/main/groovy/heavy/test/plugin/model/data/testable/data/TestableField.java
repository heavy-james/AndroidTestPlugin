package heavy.test.plugin.model.data.testable.data;

import org.gradle.internal.impldep.com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import heavy.test.plugin.model.data.Atom;
import heavy.test.plugin.model.data.TestObject;
import heavy.test.plugin.model.data.reflection.ObjectData;

/**
 * Created by heavy on 2017/6/2.
 * <p>
 * check the value of field
 */

public class TestableField extends TestableData {

    @SerializedName("field_data")
    ObjectData fieldData;
    @SerializedName("field_data_name")
    String fieldDataName;
    @SerializedName("field_atoms")
    List<Atom> fieldAtoms;

    public TestableField() {
        fieldData = new ObjectData();
        fieldAtoms = new ArrayList<>();
    }

    public ObjectData getFieldData() {
        return fieldData;
    }

    public void setFieldData(ObjectData fieldData) {
        this.fieldData = fieldData;
    }

    public String getFieldDataName() {
        return fieldDataName;
    }

    public void setFieldDataName(String fieldDataName) {
        this.fieldDataName = fieldDataName;
    }

    public List<Atom> getFieldAtoms() {
        return fieldAtoms;
    }

    public void setFieldAtoms(List<Atom> fieldAtoms) {
        this.fieldAtoms = fieldAtoms;
    }

    @Override
    public TestObject clean() {
        fieldAtoms.clear();
        return super.clean();
    }
}
