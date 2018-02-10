package heavy.test.plugin.model.data.action.view;


import com.google.gson.annotations.SerializedName;

import heavy.test.plugin.model.data.Action;

/**
 * Created by heavy on 2018/2/9.
 */

public class SetText extends Action {


    @SerializedName("content")
    private String content;

    public SetText(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
