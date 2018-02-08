package heavy.test.plugin.logic.command;

import heavy.test.plugin.logic.TestCommand;
import heavy.test.plugin.logic.TestCommandFactory;

import org.json.JSONObject;

/**
 * Created by heavy on 2017/6/20.
 */

public class RecordResult extends TestCommand {

    public static final int LEVEL_PAGE = 1;
    public static final int LEVEL_CASE = 2;
    public static final int LEVEL_TESTABLE = 3;
    public static final int LEVEL_ACTION = 4;
    public static final int LEVEL_DETAIL = 5;

    String tag;
    String info;
    boolean failed;
    int level;

    public String getInfo() {
        return info;
    }

    public RecordResult setInfo(String info) {
        this.info = info;
        return this;
    }

    public boolean isFailed() {
        return failed;
    }

    public RecordResult setFailed(boolean failed) {
        this.failed = failed;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public RecordResult setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public RecordResult setLevel(int level) {
        this.level = level;
        return this;
    }

    @Override
    public String getName() {
        return TestCommandFactory.RECORD_RESULT;
    }

    @Override
    public JSONObject getJsonObject() {
        JSONObject result = super.getJsonObject();
        result.putOpt("info", info);
        result.putOpt("failed", failed);
        result.putOpt("level", level);
        result.putOpt("tag", tag);
        return result;
    }

    @Override
    public void parseJsonObject(JSONObject object) {
        super.parseJsonObject(object);
        info = object.optString("info");
        failed = object.optBoolean("failed");
        level = object.optInt("level");
        tag = object.optString("tag");
    }
}
