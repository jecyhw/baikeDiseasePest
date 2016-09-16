package com.jecyhw.document;

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
     *  图片在mongo数据库的文件索引,引用的fs.files的_id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Picture)) return false;

        Picture picture = (Picture) o;

        if (title != null ? !title.equals(picture.title) : picture.title != null) return false;
        if (reference != null ? !reference.equals(picture.reference) : picture.reference != null) return false;
        return !(filename != null ? !filename.equals(picture.filename) : picture.filename != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        return result;
    }
}
