package com.jecyhw.service.impl;

import com.jecyhw.core.pivotviewer.DeepZoomFileTemplate;
import com.jecyhw.model.database.BaseModel;
import com.jecyhw.model.subModel.DeepZoomMetaData;
import com.jecyhw.model.subModel.Picture;
import com.jecyhw.repository.BaseOperations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jecyhw on 16-11-5.
 */
abstract public class BaseService<T extends BaseModel> {

    @Autowired
    DeepZoomFileTemplate deepZoomFileTemplate;

    abstract  protected BaseOperations<T> getRepository();

    public void save(List<T> entities) {
        getRepository().save(entities);
    }

    public void save(T entity) {
        //先检查deepZoom和pictures是否相符
        DeepZoomMetaData deepZoomMetaData = entity.getDeepZoom();
        List<Picture> pictures = entity.getPictures();
        boolean deleteFlag = true;
        if (pictures != null) {
            if (pictures.size() == 0) {
                entity.setPictures(null);
            } else if (deepZoomMetaData == null){
                entity.setDeepZoom(deepZoomFileTemplate.createDeepZoom(pictures.get(0).getFileId()));
                deleteFlag = false;
            } else {
                for (Picture picture : pictures) {
                    if (picture.getFileId().equals(deepZoomMetaData.getFileId())) {
                        deleteFlag = false;
                        break;
                    }
                }
            }
        }
        if (deepZoomMetaData != null && deleteFlag) {
            deepZoomFileTemplate.deleteDeepZoom(deepZoomMetaData.getFileId());
            entity.setDeepZoom(null);
        }
        getRepository().save(entity);
    }

    /**
     * @return 返回所有含pictures列表不为空的记录
     */
    public List<T> picturesSizeNotEmpty() {
        return getRepository().findByPicturesExists(true);
    }

    public T findById(String id) {
        return getRepository().findOne(id);
    }

    public T findByChineseName(String name) {
        return getRepository().findByChineseName(name);
    }

    public boolean deleteById(String id) {
        T entity = getRepository().findOne(id);
        if (entity != null) {
            //删除相关的deepZoom文件
            deepZoomFileTemplate.deleteDeepZoom(entity.getDeepZoom().getFileId());
            //删除记录
            getRepository().delete(id);
            return true;
        }
        return false;
    }

    /**
     * 在含有deepZoom字段的的列表中提取每个的deepZoom
     * @return
     */
    public List<DeepZoomMetaData> extractDeepZoomField() {
        List<DeepZoomMetaData> result = new ArrayList<>();
        List<T> entities = getRepository().findByPicturesExists(true);
        for (T entity : entities) {
            DeepZoomMetaData deepZoom = entity.getDeepZoom();
            if (deepZoom != null) {
                if (!deepZoomFileTemplate.fileExists(deepZoom.getFileId())) {
                    deepZoomFileTemplate.createDeepZoom(deepZoom.getFileId());
                }
                result.add(deepZoom);
            }
        }
        return result;
    }
}
