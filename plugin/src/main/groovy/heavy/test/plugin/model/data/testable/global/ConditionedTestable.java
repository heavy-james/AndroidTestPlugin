package heavy.test.plugin.model.data.testable.global;


import heavy.test.plugin.model.data.TestContext;
import heavy.test.plugin.model.data.TestObject;

import org.json.JSONObject;

import groovy.lang.Closure;

/**
 * Created by heavy on 2017/5/31.
 */

public class ConditionedTestable extends TestObject {

    boolean conversed;
    Closure conditionClosure;
    Closure trueCaseClosure;
    Closure falseCaseClosure;
    Closure preconditionClosure;
    long timeOut;
    private TestContext testContext;
    public ConditionedTestable(TestContext testContext) {
        this.testContext = testContext;
    }

    public TestContext getTestContext() {
        return testContext;
    }

    public void setTestContext(TestContext testContext) {
        this.testContext = testContext;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isConversed() {
        return conversed;
    }

    public void setConversed(boolean conversed) {
        this.conversed = conversed;
    }

    public Closure getConditionClosure() {
        return conditionClosure;
    }

    public void setConditionClosure(Closure conditionClosure) {
        this.conditionClosure = conditionClosure;
    }

    public Closure getTrueCaseClosure() {
        return trueCaseClosure;
    }

    public void setTrueCaseClosure(Closure trueCaseClosure) {
        this.trueCaseClosure = trueCaseClosure;
    }

    public Closure getFalseCaseClosure() {
        return falseCaseClosure;
    }

    public void setFalseCaseClosure(Closure falseCaseClosure) {
        this.falseCaseClosure = falseCaseClosure;
    }

    public Closure getPreconditionClosure() {
        return preconditionClosure;
    }

    public void setPreconditionClosure(Closure preconditionClosure) {
        this.preconditionClosure = preconditionClosure;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.put("converse", conversed);
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        conversed = object.optBoolean("converse");
    }
}
