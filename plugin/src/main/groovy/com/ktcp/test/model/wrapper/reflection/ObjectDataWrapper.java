package com.ktcp.test.model.wrapper.reflection;

import com.ktcp.test.model.data.reflection.ObjectData;

/**
 * Created by heavy on 2017/6/2.
 */

public class ObjectDataWrapper {

    ObjectData objectData;

    public ObjectDataWrapper(ObjectData data) {
        objectData = data;
    }

    String getActivity() {
        return ObjectData.FROM_ACTIVITY;
    }

    void container(String containerName) {
        objectData.setContainerName(containerName);
    }

    void container(ObjectData container) {
        objectData.setContainer(container);
    }

    void containerClass(String className) {
        objectData.setContainerClassName(className);
    }

    void isStatic(boolean isStatic) {
        objectData.setStatic(isStatic);
    }

    void fieldName(String fieldName) {
        objectData.setObjectName(fieldName);
    }
}
