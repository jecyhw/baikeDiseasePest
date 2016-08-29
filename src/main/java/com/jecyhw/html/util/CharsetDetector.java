package com.jecyhw.html.util;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jecyhw on 16-8-26.
 */
final public class CharsetDetector {
    static final String DEFAULT_ENCODING = "UTF-8";
    static public String guessEncoding(byte[] bytes) {
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        return encoding == null ? DEFAULT_ENCODING : encoding;
    }

    static public String guessEncoding(InputStream inputStream) throws IOException {
        UniversalDetector detector = new UniversalDetector(null);
        byte[] buf = new byte[4096];
        int nread;
        while ((nread = inputStream.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        return encoding == null ? DEFAULT_ENCODING : encoding;
    }
}
