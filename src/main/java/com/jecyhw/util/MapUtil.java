package com.jecyhw.util;

import java.util.*;

/**
 * Created by jecyhw on 16-9-7.
 */
final public class MapUtil {
    @SuppressWarnings("unchecked")
    static public List<String> uniqueKey(Map<String, Object> map) {
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            list.add(key);
            Object obj = map.get(key);
            if (obj instanceof Map) {
                list.addAll(StringUtil.addPrefix(uniqueKey((Map<String, Object>) obj), key));
            }
        }
        return list;
    }
}
