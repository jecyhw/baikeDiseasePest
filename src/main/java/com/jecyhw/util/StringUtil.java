package com.jecyhw.util;

import java.util.regex.Pattern;

/**
 * Created by jecyhw on 16-8-29.
 */
public class StringUtil {
    final static Pattern blankPattern = Pattern.compile("\\s", Pattern.UNICODE_CHARACTER_CLASS);


    public static String removeBlank(String value) {
        return replaceBlank(value, "");
    }

    public static String replaceBlank(String value, String replace) {
        return blankPattern.matcher(value).replaceAll(replace);
    }
}
