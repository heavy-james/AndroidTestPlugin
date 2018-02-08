package heavy.test.plugin.model.data.testable.data;

import heavy.test.plugin.model.data.factory.AtomFactory;
import heavy.test.plugin.model.data.interf.ITestObject;
import heavy.test.plugin.model.data.reflection.ObjectData;
import heavy.test.plugin.util.TextUtil;
import heavy.test.plugin.model.data.Atom;
import heavy.test.plugin.model.data.factory.TestableDataFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heavy on 2017/6/2.
 * <p>
 * check the value of field
 */

public class TestableField extends TestableData {

    ObjectData fieldData;
    String fieldDataName;
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
    public JSONObject getJsonObject() {
        JSONObject object = super.getJsonObject();
        if (fieldData != null) {
            object.putOpt("field_data", fieldData.getJsonObject());
        }
        if (!TextUtil.isEmpty(fieldDataName)) {
            object.put("field_data_name", fieldDataName);
        }
        if (fieldAtoms != null && fieldAtoms.size() > 0) {
            JSONArray atomArray = new JSONArray();
            for (Atom atom : fieldAtoms) {
                atomArray.put(atom.getJsonObject());
            }
            object.putOpt("field_atoms", atomArray);
        }
        return object;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        fieldData.parseJsonObject(object.optJSONObject("field_data"));
        fieldDataName = object.optString("field_data_name");
        JSONArray atomArray = object.optJSONArray("field_atoms");
        if (atomArray != null && atomArray.length() > 0) {
            for (int i = 0; i < atomArray.length(); i++) {
                Atom atom = AtomFactory.createAtom(atomArray.getJSONObject(i));
                if (atom != null) {
                    fieldAtoms.add(atom);
                }
            }
        }
    }

    @Override
    public String getTestableDataType() {
        return TestableDataFactory.TYPE_FIELD;
    }

    @Override
    public ITestObject clean() {
        fieldAtoms.clear();
        return this;
    }
}
