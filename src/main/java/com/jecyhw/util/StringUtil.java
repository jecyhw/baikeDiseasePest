package com.jecyhw.util;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by jecyhw on 16-8-29.
 */
final public class StringUtil {
    final static Pattern blankPattern = Pattern.compile("\\s", Pattern.UNICODE_CHARACTER_CLASS);


    static public String removeBlank(String value) {
        return replaceBlank(value, "");
    }

    static public String replaceBlank(String value, String replace) {
        return blankPattern.matcher(value).replaceAll(replace);
    }

    static public List<String> addPrefix(List<String> list, String prefix, String separator) {
        List<String> newList = new ArrayList<>();
        for (String value : list) {
            newList.add(prefix + separator + value);
        }
        return newList;
    }

    static public List<String> addPrefix(List<String> list, String prefix) {
        return addPrefix(list, prefix, ".");
    }
}
