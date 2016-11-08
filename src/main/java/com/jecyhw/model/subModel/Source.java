package com.jecyhw.model.subModel;

/**
 * Created by jecyhw on 16-10-28.
 */
public enum Source {

    BAI_KE("百度百科"),
    HAND("手工录入");

    String source;

    Source(String source) {
        this.source = source;
    }
}
