package com.jecyhw.html.baidu;

import com.jecyhw.html.request.Page;
import com.jecyhw.html.util.CharsetDetector;
import com.jecyhw.html.util.RequestUtil;
import com.jecyhw.html.request.params.Get;
import okhttp3.Headers;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by jecyhw on 16-8-26.
 */
public class BaikeSerach implements Page {

    final String url;

    String responseUrl;

    public BaikeSerach(Params params) {
        this.url = RequestUtil.urlWithGetParams(BaikeConstant.SEARCH_URL, params);
    }


    @Override
    public String html() throws IOException {
        Response response = RequestUtil.getResponse(url);
        responseUrl = response.request().url().toString();
        if (responseUrl.endsWith(".htm")) {
            byte[] bytes = response.body().bytes();
            String encoding = CharsetDetector.guessEncoding(bytes);
            return new String(bytes, encoding);
        } else {
            throw new IOException(responseUrl);
        }
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    static public class Params implements Get{

        final String word;

        public Params(String word) {
            this.word = word;
        }

        @Override
        public String params() {
            return "word=" + word;
        }

        @Override
        public String toString() {
            return "Params{" +
                    "word='" + word + '\'' +
                    '}';
        }
    }

    private Headers headers() {
        Headers.Builder builder = new Headers.Builder();
        builder.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        builder.add("Accept-Encoding", "gzip, deflate, sdch");
        builder.add("Accept-Language", "en-US,en;q=0.8");
        builder.add("Connection", "keep-alive");
        builder.add("Host", "baike.baidu.com");
        builder.add("Referer", "http://baike.baidu.com/");
        builder.add("Upgrade-Insecure-Requests", "1");
        builder.add("Referer", "http://baike.baidu.com/");
        builder.add("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
        builder.add("Cookie", "BAIDUID=86F646652620495C82E751EE9540E861:FG=1; BIDUPSID=86F646652620495C82E751EE9540E861; PSTM=1471776676; BDUSS=k4V35HaEluUkFYaXRSQ3ZTb3BWfjAwSUZnQ045bFY0Tk1ubWRLcmwzNTZrT05YQVFBQUFBJCQAAAAAAAAAAAEAAAC9dgsjueLUzsTQuqIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHoDvFd6A7xXcT; pgv_pvi=5457654784; pgv_si=s164631552; locale=zh; BDRCVFR[WLjnenwr-vY]=G4oDd77ZYN6ph-oug9dug-dmy38mvqV; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; H_PS_PSSID=1434_17001_12115_20856_20732_20837_20781");
        return builder.build();
    }
}
