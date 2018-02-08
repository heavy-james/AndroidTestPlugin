package heavy.test.plugin.model.wrapper.reflection;

import heavy.test.plugin.model.data.reflection.ObjectData;
import heavy.test.plugin.model.data.reflection.MethodData;

import groovy.lang.Closure;

/**
 * Created by heavy on 2017/6/2.
 */

public class MethodDataWrapper {

    MethodData methodData;

    public MethodDataWrapper(MethodData data) {
        methodData = data;
    }

    void instance(ObjectData data) {
        methodData.setContainer(data);
    }

    void instance(String dataName) {
        methodData.setContainerName(dataName);
    }

    void methodName(String methodName) {
        methodData.setMethodName(methodName);
    }

    void isStatic(boolean isStatic) {
        methodData.setStatic(isStatic);
    }

    void isConstructor(boolean isConstructor) {
        methodData.setConstructor(isConstructor);
    }

    void args(Closure closure) {
        MethodArgsWrapper wrapper = new MethodArgsWrapper();
        closure.setDelegate(wrapper);
        closure.setResolveStrategy(Closure.DELEGATE_ONLY);
        closure.call();
        methodData.setArgList(wrapper.getArgObjects());
    }

    void instanceClass(String containerClassName) {
        methodData.setContainerClassName(containerClassName);
    }


}
