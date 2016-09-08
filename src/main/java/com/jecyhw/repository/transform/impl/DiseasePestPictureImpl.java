package com.jecyhw.repository.transform.impl;

import com.jecyhw.document.DiseasePest;
import com.jecyhw.document.Paragraph;
import com.jecyhw.document.Picture;
import com.jecyhw.repository.transform.PictureTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by jecyhw on 16-8-30.
 */
@Component
final public class DiseasePestPictureImpl implements PictureTransform<DiseasePest> {

    Logger logger = LoggerFactory.getLogger(DiseasePestPictureImpl.class);

    Map<String, Picture> linkPictures;

    /**
     * 要想获取DiseasePest的图片信息,必须先执行transform方法
     * @return
     */
    @Override
    public List<Picture> pictures() {
        return new ArrayList<>(linkPictures.values());
    }

    /**
     * 由于图片下载保存涉及到数据库操作,所以下载保存功能不在此类中实现,提供pictures方法获取该病虫害的图片相关信息,所以调用此方法只是
     * 拷贝了一份DiseasePest,如果传入的对象图片信息未下载入库,将会对图片信息进行整理,去重,然后可通过picture获取所有图片信息,供外部
     * 调用决定在哪下载入库,这里是在DiseasePestRepositoryImpl类中保存入库的
     * @param diseasePest
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public DiseasePest transform(DiseasePest diseasePest) {
        this.linkPictures = new HashMap<>();
        DiseasePest newDiseasePest = new DiseasePest();
        newDiseasePest.setId(diseasePest.getId());
        newDiseasePest.setName(diseasePest.getName());
        newDiseasePest.setSource(diseasePest.getSource());
        newDiseasePest.setType(diseasePest.getType());
        newDiseasePest.setIntroduction(map(diseasePest.getIntroduction()));
        return newDiseasePest;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> map(Map<String, Object> map) {
        logger.debug("map method");
        Map<String, Object> newMap = new LinkedHashMap<>();
        for (String key : map.keySet()) {
            Object obj = map.get(key);
            logger.debug("key:{}, value class type:{}", key, obj.getClass());
            if (obj instanceof List) {
                newMap.put(key, list((List<Object>) obj));
            } else if (obj instanceof Map) {
                newMap.put(key, map((Map<String, Object>) obj));
            } else {
                newMap.put(key, obj);
            }
        }
        return newMap;
    }

    @SuppressWarnings("unchecked")
    private List<Object> list(List<Object> list) {
        List<Object> newList = new ArrayList<>();
        for (Object obj : list) {
            if (obj instanceof Paragraph) {
                newList.add(paragraph((Paragraph) obj));
            } else if (obj instanceof Picture) {
                newList.add(picture((Picture) obj));
            } else if (obj instanceof List) {
                newList.add(list((List<Object>) obj));
            }
        }
        return newList;
    }

    @SuppressWarnings("unchecked")
    private Paragraph paragraph(Paragraph paragraph) {
        Paragraph newParagraph = new Paragraph();
        newParagraph.setContent(paragraph.getContent());
        List<Object> pictures = new ArrayList<>();
        for (Object obj : paragraph.getPictures()) {
            if (obj instanceof List) {
                pictures.add(list((List<Object>) obj));
            } else if (obj instanceof Picture) {
                pictures.add(picture((Picture) obj));
            }
        }
        newParagraph.setPictures(pictures);
        return newParagraph;
    }

    /**
     * 获取图片,
     * @param picture
     */
    private Picture picture(Picture picture) {
        String link = picture.getReference();
        if (!linkPictures.containsKey(link)) {
            Picture newPicture = new Picture();
            newPicture.setReference(picture.getReference());
            newPicture.setFilename(picture.getFilename());
            newPicture.setTitle(picture.getTitle());
            linkPictures.put(link, newPicture);
            return newPicture;
        } else {
           return linkPictures.get(link);
        }
    }
}
