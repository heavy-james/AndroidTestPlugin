package com.ktcp.test.logic.command;

import com.ktcp.test.logic.TestCommand;
import com.ktcp.test.logic.TestCommandFactory;

/**
 * Created by heavy on 17/6/28.
 */

public class StopTest extends TestCommand {

    @Override
    public String getName() {
        return TestCommandFactory.STOP_TEST;
    }
}
