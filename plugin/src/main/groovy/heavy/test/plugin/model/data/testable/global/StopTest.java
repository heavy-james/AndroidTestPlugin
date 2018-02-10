package heavy.test.plugin.model.data.testable.global;

/**
 * Created by heavy on 2017/6/1.
 */

public class StopTest extends GlobalTestable {

    public StopTest(String msg) {
        description = msg;
    }

    public String getStopMessage() {
        return description;
    }

    public void setStopMessage(String msg) {
        description = msg;
    }
}
