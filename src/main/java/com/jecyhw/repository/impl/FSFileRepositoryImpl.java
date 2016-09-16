package com.jecyhw.repository.impl;

import com.jecyhw.model.database.FSFile;
import com.jecyhw.repository.custom.CustomFSFileRepository;
import com.jecyhw.util.DeepZoomUtil;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Created by jecyhw on 16-9-12.
 */
@Repository
public class FSFileRepositoryImpl implements CustomFSFileRepository {

    @Autowired
    GridFsOperations gridFsOperations;

    @Autowired
    MongoOperations mongoOperations;

    public FSFile deepZoomFile(String id) {
        try {
            if (!DeepZoomUtil.directoryExists(id)) {//切图不存在
                GridFSDBFile fsdbFile = gridFsOperations.findOne(Query.query(Criteria.where("_id").is(id)));
                FSFile fsFile = new FSFile();
                fsFile.setMetaData(DeepZoomUtil.buildPyramid(fsdbFile.getInputStream(), id));
                fsFile.setId(fsdbFile.getId().toString());
                fsFile.setFilename(fsdbFile.getFilename());
                fsdbFile.getMetaData().removeField("width");
                fsdbFile.getMetaData().removeField("height");
                mongoOperations.save(fsFile);
                return fsFile;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deepZoomFile(FSFile file) {
        try {
            String id = file.getId();
            if (!DeepZoomUtil.fileExists(file.getId())) {//切图不存在
                GridFSDBFile fsdbFile = gridFsOperations.findOne(Query.query(Criteria.where("_id").is(id)));
                DeepZoomUtil.buildPyramid(fsdbFile.getInputStream(), id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
