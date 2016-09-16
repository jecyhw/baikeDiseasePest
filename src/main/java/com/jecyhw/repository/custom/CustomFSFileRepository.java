package com.jecyhw.repository.custom;

import com.jecyhw.model.database.FSFile;

/**
 * Created by jecyhw on 16-9-14.
 */
public interface CustomFSFileRepository {

    FSFile deepZoomFile(String id);
    void deepZoomFile(FSFile file);
}
