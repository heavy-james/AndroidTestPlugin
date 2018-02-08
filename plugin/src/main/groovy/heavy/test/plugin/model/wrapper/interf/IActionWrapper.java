package heavy.test.plugin.model.wrapper.interf;

import heavy.test.plugin.model.data.Action;

/**
 * Created by heavy on 2017/5/25.
 */

public interface IActionWrapper extends IKeyEventWrapper {

    public Action setChecked(boolean check);

    public Action setFocused(boolean focused);

    public Action click();

    public Action delay(long delayMillis);
}
