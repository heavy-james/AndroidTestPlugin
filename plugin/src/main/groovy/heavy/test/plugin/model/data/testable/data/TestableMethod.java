package heavy.test.plugin.model.data.testable.data;

import org.gradle.internal.impldep.com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import heavy.test.plugin.model.data.Atom;
import heavy.test.plugin.model.data.TestObject;
import heavy.test.plugin.model.data.reflection.MethodData;

/**
 * Created by heavy on 2017/6/2.
 * <p>
 * check the result of method call
 */

public class TestableMethod extends TestableData {
    @SerializedName("method_data")
    MethodData methodData;
    @SerializedName("method_data_name")
    String methodDataName;
    @SerializedName("method_atoms")
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
    public TestObject clean() {
        methodAtoms.clear();
        return super.clean();
    }
}
