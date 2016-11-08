package com.jecyhw.core.file;

import java.io.InputStream;

/**
 * 对于上传文件怎么处理的接口
 * Created by jecyhw on 16-11-3.
 */
public interface FileOperations {
    /**
     * @param content
     * @return 保存文件流,并返回文件的索引id
     */
    String store(InputStream content);

    /**
     * @param fileId
     * @return 根据文件索引获取文件流
     */
    InputStream findByFileIdAsInputStream(String fileId);

    /**
     * 根据文件索引删除文件
     * @param fileId
     */
    void delete(String fileId);
}
