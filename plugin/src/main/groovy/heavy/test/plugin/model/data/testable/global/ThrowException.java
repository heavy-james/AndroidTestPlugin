package heavy.test.plugin.model.data.testable.global;

/**
 * Created by heavy on 2017/6/1.
 */

public class ThrowException extends GlobalTestable {

    public ThrowException(String msg) {
        description = msg;
    }

    public String getExceptionMessage() {
        return description;
    }
}
