package com.ktcp.test.model.data.testable.data;

import com.ktcp.test.model.data.Atom;
import com.ktcp.test.model.data.factory.AtomFactory;
import com.ktcp.test.model.data.factory.TestableDataFactory;
import com.ktcp.test.model.data.interf.ITestObject;
import com.ktcp.test.model.data.reflection.MethodData;
import com.ktcp.test.util.TextUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heavy on 2017/6/2.
 * <p>
 * check the result of method call
 */

public class TestableMethod extends TestableData {
    MethodData methodData;
    String methodDataName;
    List<Atom> methodAtoms;

    public TestableMethod() {
        methodAtoms = new ArrayList<>();
        methodData = new MethodData();
    }

    public MethodData getMethodData() {
        return methodData;
    }

    public void setMethodData(MethodData methodData) {
        this.methodData = methodData;
    }

    public String getMethodDataName() {
        return methodDataName;
    }

    public void setMethodDataName(String methodDataName) {
        this.methodDataName = methodDataName;
    }

    public List<Atom> getMethodAtoms() {
        return methodAtoms;
    }

    public void setMethodAtoms(List<Atom> methodAtoms) {
        this.methodAtoms = methodAtoms;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject object = super.getJsonObject();
        if (methodData != null) {
            object.putOpt("method_data", methodData.getJsonObject());
        }
        if (!TextUtil.isEmpty(methodDataName)) {
            object.put("method_data_name", methodDataName);
        }
        if (methodAtoms != null && methodAtoms.size() > 0) {
            JSONArray atomArray = new JSONArray();
            for (Atom atom : methodAtoms) {
                atomArray.put(atom.getJsonObject());
            }
            object.putOpt("method_atoms", atomArray);
        }
        return object;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        JSONObject methodObject = object.optJSONObject("method_data");
        if (methodObject != null) {
            methodData = new MethodData();
            methodData.parseJsonObject(methodObject);
        }
        methodDataName = object.optString("method_data_name");
        JSONArray atomArray = object.optJSONArray("method_atoms");
        if (atomArray != null && atomArray.length() > 0) {
            for (int i = 0; i < atomArray.length(); i++) {
                Atom atom = AtomFactory.createAtom(atomArray.getJSONObject(i));
                if (atom != null) {
                    methodAtoms.add(atom);
                }
            }
        }
    }

    @Override
    public String getTestableDataType() {
        return TestableDataFactory.TYPE_METHOD;
    }

    @Override
    public ITestObject clean() {
        methodAtoms.clear();
        return this;
    }
}
