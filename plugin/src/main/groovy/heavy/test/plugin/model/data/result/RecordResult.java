package heavy.test.plugin.model.data.result;

import org.gradle.internal.impldep.com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.TestResult;

/**
 * Created by heavy on 2017/6/20.
 */

public class RecordResult extends TestResult {

    public static final int LEVEL_PAGE = 1;
    public static final int LEVEL_CASE = 2;
    public static final int LEVEL_TESTABLE = 3;
    public static final int LEVEL_ACTION = 4;
    public static final int LEVEL_DETAIL = 5;

    @SerializedName("tag")
    String tag;

    @SerializedName("info")
    String info;

    @SerializedName("level")
    int level;

    public String getInfo() {
        return info;
    }

    public RecordResult setInfo(String info) {
        this.info = info;
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
}
