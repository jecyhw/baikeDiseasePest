package com.jecyhw.model.document;

/**
 * Created by jecyhw on 16-8-27.
 */
public class Picture {
    /**
     * 图片标题
     */
    String title;

    /**
     * 图片引用来源(百科)
     */
    String reference;

    /**
     *  图片的名字(在mongo数据库的文件名字)
     */
    String filename;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
