package com.jecyhw.model.document;

import java.util.List;

/**
 * Created by jecyhw on 16-8-27.
 */
public class Paragraph {
    String content;
    List<Object> pictures;//包含1. 列表图片|2. 图片|3. 1和2组合

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Object> getPictures() {
        return pictures;
    }

    public void setPictures(List<Object> pictures) {
        this.pictures = pictures;
    }
}
