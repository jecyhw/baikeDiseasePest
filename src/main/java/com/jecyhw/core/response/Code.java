package com.jecyhw.core.response;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by jecyhw on 16-8-28.
 */
public enum Code {
    SUCCESS(200),
    FAIL(0),
    CREATE_FAIL(400),
    NOT_FOUND(404);

    int code;

    private Code(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Code{" +
                "code=" + code +
                '}';
    }
}
