
package com.jecyhw.core.response;

/**
 * Created by jecyhw on 16-8-27.
 */
final public class Response<T> {
    private final Code code;
    private final T message;

    private Response(Code code, T message) {
        this.code = code;
        this.message = message;
    }

    public Code getCode() {
        return code;
    }

    public T getMessage() {
        return message;
    }

    static public <T> Response<T> success(T message) {
        return create(Code.SUCCESS, message);
    }

    static public <T> Response<T> fail(T message) {
        return create(Code.FAIL, message);
    }

    static public <T> Response<T> notFound(T message) {
        return create(Code.NOT_FOUND, message);
    }

    static public <T> Response<T> createFail(T message) {
        return create(Code.CREATE_FAIL, message);
    }

    static private  <T> Response<T> create(Code code, T message) {
        return new Response<>(code, message);
    }
}
