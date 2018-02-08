package heavy.test.plugin.logic;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

import heavy.test.plugin.logic.command.RecordResult;
import heavy.test.plugin.util.FileUtil;
import heavy.test.plugin.util.TextUtil;

/**
 * Created by heavy on 2017/6/1.
 */

public class TestResultRecorder {


    public static final String DEFAULT_RESULT_FILE_NAME = "/sdcard/test_result/" + "test_result.txt";
    private static final String TAG = "TestResultRecorder";
    public boolean isWriteThreadLive;
    String mResultFileName;
    public boolean recordResult = true;
    private ConcurrentLinkedQueue<ActionInfo> tempQueue = new ConcurrentLinkedQueue<ActionInfo>();

    public TestResultRecorder() {
        this(DEFAULT_RESULT_FILE_NAME);
    }

    public TestResultRecorder(String resultFleName) {
        mResultFileName = resultFleName;
        if (isLogFileExist()) {
            deleteLogFile();
        }
    }

    public void setResultFile(String resultFile) {
        mResultFileName = resultFile;
        if (isLogFileExist()) {
            deleteLogFile();
        }
    }

    public void setNeedRecordResult(boolean recordResult) {
        this.recordResult = recordResult;
    }

    public boolean needRecordResult() {
        return recordResult;
    }


    public void record(RecordResult recordResult) {

        switch (recordResult.getLevel()) {
            case RecordResult.LEVEL_PAGE:
                writePageInfo(recordResult.getTag(), recordResult.getInfo());
                break;
            case RecordResult.LEVEL_CASE:
                writeCaseInfo(recordResult.getTag(), recordResult.getInfo());
                break;
            case RecordResult.LEVEL_TESTABLE:
                writeTestableInfo(recordResult.getTag(), recordResult.getInfo());
                break;
            case RecordResult.LEVEL_ACTION:
                writeActionInfo(recordResult.getTag(), recordResult.getInfo());
                break;
            case RecordResult.LEVEL_DETAIL:
                writeDetailInfo(recordResult.getTag(), recordResult.getInfo());
                break;
        }
    }

    public void writePageInfo(String tag, String message) {
        writeAction(new ActionInfo(ActionInfo.PAGE, tag, message));
    }

    public void writeCaseInfo(String tag, String message) {
        writeAction(new ActionInfo(ActionInfo.CASE, tag, message));
    }

    public void writeTestableInfo(String tag, String message) {
        writeAction(new ActionInfo(ActionInfo.TESTABLE, tag, message));
    }

    public void writeActionInfo(String tag, String message) {
        writeAction(new ActionInfo(ActionInfo.ACTION, tag, message));
    }

    public void writeDetailInfo(String tag, String message) {
        writeAction(new ActionInfo(ActionInfo.DETAIL, tag, message));
    }

    public void writeResult(String tag, String message) {
        writeAction(new ActionInfo(ActionInfo.TOP, tag, message));
    }

    private void writeAction(ActionInfo ai) {
        tempQueue.add(ai);
        if (!isWriteThreadLive) {
            new WriteThread().start();
        }
    }

    /**
     * 打开日志文件并写入日志
     *
     * @return
     */
    private void recordStringLog(String text) {
        FileUtil.writeFile(mResultFileName, text, true);
    }

    /**
     * 判断日志文件是否存在
     *
     * @return
     */
    private boolean isLogFileExist() {
        File file = new File(mResultFileName);
        return file.exists() && file.length() > 3;
    }

    /**
     * 删除日志文件
     */
    private void deleteLogFile() {
        File file = new File(mResultFileName);
        if (file.exists()) {
            file.delete();
        }
    }

    static class ActionInfo {

        private static final int TOP = 0;
        private static final int PAGE = 1;
        private static final int CASE = 2;
        private static final int TESTABLE = 3;
        private static final int ACTION = 4;
        private static final int DETAIL = 5;

        private static DateFormat mDateFormat = SimpleDateFormat.getTimeInstance();
        private int mLevel = 0;
        private String mTag = "";
        private String mContent = "";

        private ActionInfo(int level, String tag, String content) {
            mTag = tag;
            mContent = content;
            mLevel = level;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(mDateFormat.format(new Date()) + " ");
            for (int i = 0; i < mLevel; i++) {
                sb.append(TextUtil.STD_INDENT);
            }
            sb.append(mContent);
            return sb.toString();
        }
    }

    private class WriteThread extends Thread {

        private WriteThread() {
            isWriteThreadLive = true;
        }

        @Override
        public void run() {
            isWriteThreadLive = true;
            while (!tempQueue.isEmpty()) {// 对列不空时
                try {
                    // 写日志到SD卡
                    recordStringLog(tempQueue.poll().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 队列中的日志都写完了，关闭线程（也可以常开 要测试下）
            isWriteThreadLive = false;
        }
    }
}
