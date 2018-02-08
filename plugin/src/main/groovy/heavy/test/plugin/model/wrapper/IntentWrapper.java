package heavy.test.plugin.model.wrapper;

import heavy.test.plugin.model.data.IntentData;

import groovy.lang.Closure;

/**
 * Created by heavy on 2017/5/20.
 */

public class IntentWrapper {

    IntentData mIntentData;

    public IntentWrapper() {
        mIntentData = new IntentData();
    }

    void action(String action) {
        mIntentData.setAction(action);
    }

    void className(String clazz) {
        mIntentData.setClassName(clazz);
    }

    void category(String category) {
        mIntentData.setCategory(category);
    }

    void flag(int flag) {
        mIntentData.setFlag(flag);
    }

    void packageName(String packageName) {
        mIntentData.setPackageName(packageName);
    }

    void extras(Closure closure) {
        ExtraWrapper wrapper = new ExtraWrapper();
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.setDelegate(wrapper);
        closure.call();
        mIntentData.setExtras(wrapper.getExtras());
    }

    public IntentData getIntent() {
        return mIntentData;
    }

}
