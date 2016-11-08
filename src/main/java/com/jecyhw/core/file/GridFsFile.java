package com.jecyhw.core.file;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import java.io.InputStream;
import java.util.UUID;

/**
 * GridFs文件操作类,保存、获取文件流,删除文件
 * Created by jecyhw on 16-11-1.
 */
public class GridFsFile implements FileOperations{
    @Autowired
    private GridFsOperations gridFsOperations;

    /**
     * @param fileId
     * @return 根据fileId获取mongodb中的文件流,不存在返回null
     */
    public InputStream findByFileIdAsInputStream(String fileId) {
        GridFSDBFile fsdbFile = gridFsOperations.findOne(createQueryById(fileId));
        if (fsdbFile != null) {
            return fsdbFile.getInputStream();
        }
        return null;
    }

    /**
     * @param content
     * @return 保存文件流到mongo gridfs中,返回文件在mongo gridfs的id
     */
    public String store(InputStream content) {
        GridFSFile gridFSFile = gridFsOperations.store(content, UUID.randomUUID().toString());
        return gridFSFile.getId().toString();
    }

    public void delete(String fileId) {
        gridFsOperations.delete(createQueryById(fileId));
    }

    private Query createQueryById(String fileId) {
        return Query.query(Criteria.where("_id").is(fileId));
    }
}
