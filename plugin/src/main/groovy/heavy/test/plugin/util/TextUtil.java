package heavy.test.plugin.util;

import java.util.Random;

public class TextUtil {

    public static final String STD_INDENT = "    ";

    public static final String RANDOM_SOURCE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static boolean isEmpty(String content) {
        return content == null || content.equals("");
    }

    public static boolean equals(String str1, String str2) {
        return null != str1 && str1.equals(str2);
    }

    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            sb.append(RANDOM_SOURCE.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }
}
