package heavy.test.plugin.model.data.testable.global;

/**
 * Created by heavy on 2017/6/1.
 */

public class SendMessage extends GlobalTestable {

    public SendMessage(String msg) {
        description = msg;
    }

    public String getMessage() {
        return description;
    }

    public void setMessage(String msg) {
        description = msg;
    }
}
