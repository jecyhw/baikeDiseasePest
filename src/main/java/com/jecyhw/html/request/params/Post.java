package com.jecyhw.html.request.params;

import okhttp3.RequestBody;

/**
 * Created by jecyhw on 16-8-18.
 * OkHttp的Request请求post参数构造
 */
public interface Post {
    /**
     *
     * @return 返回Okhttp的post方法所需的RequestBody
     */
    RequestBody requestBody();
}
