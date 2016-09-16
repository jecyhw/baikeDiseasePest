package com.jecyhw.model.database;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jecyhw on 16-9-12.
 */
@Document(collection = "deepZoomMetaData")
public class FSFile {

    /**
     * 引用图片文件的id作为主键
     */
    @Id
    private String id;

    private String filename;

    private MetaData metaData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Integer getWidth() {
        return metaData.getWidth();
    }


    public Integer getHeight() {
        return metaData.getHeight();
    }


    static public class MetaData {

        private Integer width;
        private Integer height;

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }
    }
}
