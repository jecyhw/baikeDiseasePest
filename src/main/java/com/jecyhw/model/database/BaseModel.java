package com.jecyhw.model.database;

import com.jecyhw.model.subModel.DeepZoomMetaData;
import com.jecyhw.model.subModel.Picture;
import com.jecyhw.model.subModel.Source;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by jecyhw on 16-11-6.
 */
public class BaseModel {
    @Id
    private String id;

    /**
     * pivotViewer的展示图片,图片如果未指定,默认使用pictures里面第一张图片,如果存在的话。
     */
    @Field("deep_zoom")
    private DeepZoomMetaData deepZoom;
    /**
     * 图片列表
     */

    private List<Picture> pictures;

    /**
     * 记录的来源.手工录入的人或者百度百科
     */
    private Source source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public DeepZoomMetaData getDeepZoom() {
        return deepZoom;
    }

    public void setDeepZoom(DeepZoomMetaData deepZoom) {
        this.deepZoom = deepZoom;
    }
}
