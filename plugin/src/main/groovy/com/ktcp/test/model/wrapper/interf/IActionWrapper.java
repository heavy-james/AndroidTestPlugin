package com.ktcp.test.model.wrapper.interf;

import com.ktcp.test.model.data.Action;

/**
 * Created by heavy on 2017/5/25.
 */

public interface IActionWrapper extends IKeyEventWrapper {

    public Action setChecked(boolean check);

    public Action setFocused(boolean focused);

    public Action click();

    public Action delay(long delayMillis);
}
