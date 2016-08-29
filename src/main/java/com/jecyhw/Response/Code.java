package com.jecyhw.Response;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by jecyhw on 16-8-28.
 */
public enum Code {
    SUCCESS(0),
    FAIL(1);

    int code;

    private Code() {
    }

    private Code(int code) {

    }

    @JsonValue
    public int getCode() {
        return code;
    }


}
