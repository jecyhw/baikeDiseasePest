package com.jecyhw.repository.custom.impl;

import com.google.common.collect.ImmutableList;
import com.jecyhw.html.util.RequestUtil;
import com.jecyhw.model.database.Pest;
import com.jecyhw.document.DiseasePest;
import com.jecyhw.document.Picture;
import com.jecyhw.repository.DiseasePestRepository;
import com.jecyhw.repository.custom.DiseasePestTransformRepository;
import com.jecyhw.repository.custom.DocumentFieldRepository;
import com.jecyhw.repository.transform.PictureTransform;
import com.jecyhw.repository.transform.Transform;
import com.jecyhw.util.MapUtil;
import com.jecyhw.util.StringUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.IOException;
import java.util.*;

/**
 * Created by jecyhw on 16-9-7.
 */
public class DiseasePestRepositoryImpl implements DiseasePestTransformRepository, DocumentFieldRepository<DiseasePest> {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    DiseasePestRepository diseasePestRepository;

    @Autowired
    PictureTransform<DiseasePest> diseasePestPictureTransform;

    @Autowired
    Transform<DiseasePest, Pest> pestTransform;

    @Override
    public DiseasePest transformPictures(DiseasePest diseasePest) {
        DiseasePest transformDiseasePest = diseasePestPictureTransform.transform(diseasePest);
        List<Picture> pictures = diseasePestPictureTransform.pictures();
        savePicturesToDatabase(pictures);
        diseasePestRepository.save(transformDiseasePest);
        return transformDiseasePest;
    }

    @Override
    public Pest transformPest(DiseasePest diseasePest) {
        return pestTransform.transform(diseasePest);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<String> collectionFields(List<DiseasePest> allData) {
        Set<String> set = new TreeSet<>();
        set.add("name");
        set.add("source");
        set.add("type");
        set.add("introduction");
        for (DiseasePest diseasePest : allData) {
            set.addAll(StringUtil.addPrefix(MapUtil.uniqueKey((Map<String, Object>) diseasePest.getIntroduction()), "introduction"));
        }
        return set;
    }

    @Override
    public Set<String> collectionFields() {
        return collectionFields(ImmutableList.copyOf(diseasePestRepository.findAll()));
    }

    private void savePicturesToDatabase(List<Picture> pictures) {
        for (Picture picture : pictures) {
            savePictureToDatabase(picture);
        }
    }

    private void savePictureToDatabase(Picture picture) {
        if (picture.getFilename() != null) {
            GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(new Query(new Criteria("filename").is(picture.getFilename())));
            if (gridFSDBFile != null) {//数据库中图片已经存在
                return;
            }
        } else {
            picture.setFilename(UUID.randomUUID() + ".jpg");
        }
        try {
            DBObject metaDate = new BasicDBObject("title", picture.getTitle());
            gridFsTemplate.store(RequestUtil.getResponse(picture.getReference()).body().byteStream(), picture.getFilename(), "image/jpg", metaDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
