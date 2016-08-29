package com.jecyhw.html.util;

import com.jecyhw.html.request.params.Get;
import com.jecyhw.html.request.params.Post;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by jecyhw on 16-8-19.
 */
final public class RequestUtil {
    static public String post(String url, Post params) throws IOException {
        Request request = requestBuilder().url(url).post(params.requestBody()).build();
        return html(response(request));
    }

    static public String get(String url) throws IOException{
        return html(getResponse(url));
    }

    static public String get(String url, Headers headers) throws IOException {
        Request request = requestBuilder().url(url).headers(headers).build();
        return html(response(request));
    }

    static public Document getDocument(String url) throws IOException {
        return Jsoup.parse(get(url));
    }

    static public Response getResponse(String url) throws IOException {
        Request request = requestBuilder().url(url).build();
        return response(request);
    }

    static public Response getResponse(String url, Headers headers) throws IOException {
        Request request = requestBuilder().url(url).headers(headers).build();
        return response(request);
    }

    static public Response response(Request request) throws IOException {
        return new OkHttpClient().newCall(request).execute();
    }

    /**
     * 获取response的html文本
     * @param response
     * @return
     * @throws IOException
     */
    static private String html(Response response) throws IOException {
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException(response.toString());
        }
    }

    static public String urlWithGetParams(String url, Get params) {
        return url + "?" + params.params();
    }

    static private Request.Builder requestBuilder() {
        return new Request.Builder();
    }
}
