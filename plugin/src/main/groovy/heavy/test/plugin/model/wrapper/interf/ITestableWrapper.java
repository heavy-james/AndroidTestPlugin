package heavy.test.plugin.model.wrapper.interf;

import heavy.test.plugin.model.data.Testable;

import groovy.lang.Closure;

/**
 * Created by heavy on 2017/5/31.
 */

public interface ITestableWrapper {

    Testable withView(Closure closure);

    Testable withAdapterView(Closure closure);

    Testable withRecyclerView(Closure closure);

    Testable addView(String name, Closure closure);

    Testable addAdapterView(String name, Closure closure);

    Testable addRecyclerView(String name, Closure closure);

    Testable getView(String name, Closure closure);

    Testable makeToast(String msg, long time);

    Testable makeToast(String msg);

    Testable printMessage(String msg);

    Testable stopTest(String msg);

    Testable throwException(String msg);

    Testable delay(long time);

    Testable getAdapterView(String name, Closure closure);

    Testable getRecyclerView(String name, Closure closure);
}

