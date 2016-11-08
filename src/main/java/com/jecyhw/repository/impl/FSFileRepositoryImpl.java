package com.jecyhw.repository.impl;

import com.jecyhw.model.subModel.DeepZoomMetaData;
import com.jecyhw.model.subModel.Picture;
import com.jecyhw.repository.custom.CustomFSFileRepository;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

/**
 * Created by jecyhw on 16-9-12.
 */
@Deprecated
@Repository
public class FSFileRepositoryImpl implements CustomFSFileRepository {

    @Autowired
    GridFsOperations gridFsOperations;

    @Autowired
    MongoOperations mongoOperations;

    public DeepZoomMetaData deepZoomFile(String fileId) {
//
        return null;
    }

    @Override
    public InputStream picture(String fileId) {
        GridFSDBFile fsdbFile = gridFsOperations.findOne(Query.query(Criteria.where("_id").is(fileId)));
        if (fsdbFile != null) {
            return fsdbFile.getInputStream();
        }
        return null;
    }

    @Override
    public void deepZoomFile(Picture picture) {

    }
}
