package com.jecyhw.Response;

/**
 * Created by jecyhw on 16-8-27.
 */
final public class Response<T> {
    final Code code;
    final T message;

    public Response(Code code, T message) {
        this.code = code;
        this.message = message;
    }

    public Code getCode() {
        return code;
    }

    public T getMessage() {
        return message;
    }

    static public<T> Response<T> success(T message) {
        return new Response<>(Code.SUCCESS, message);
    }

    static public<T> Response<T> fail(T message) {
        return new Response<>(Code.FAIL, message);
    }
}
