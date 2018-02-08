package heavy.test.plugin.logic.command;

import heavy.test.plugin.logic.TestCommand;
import heavy.test.plugin.logic.TestCommandFactory;

/**
 * Created by heavy on 17/6/28.
 */

public class StopTest extends TestCommand {

    @Override
    public String getName() {
        return TestCommandFactory.STOP_TEST;
    }
}
