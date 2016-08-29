package com.jecyhw.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by jecyhw on 16-8-29.
 */
final public class JsonUtil {

    final static ObjectMapper objectMapper = new ObjectMapper();

    static public String valueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
