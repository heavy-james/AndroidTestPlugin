package heavy.test.plugin.model.wrapper.interf;

import heavy.test.plugin.model.data.Action;

/**
 * Created by heavy on 2017/6/1.
 */

public interface IKeyEventWrapper {

    public Action keyEvent(int keyCode, String eventAction);

    public Action pressKey(int keyCode);

    public Action pressBack();

    public Action pressMenu();

    public Action pressDpadCenter();

    public Action pressDpadUp();

    public Action pressDpadDown();

    public Action pressDpadLeft();

    public Action pressDpadRight();

    public Action pressVolumeDown();

    public Action pressHome();

    public Action pressPower();

    public Action pressVolumeUp();
}
