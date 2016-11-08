package com.jecyhw.repository.custom;

import com.jecyhw.model.subModel.DeepZoomMetaData;
import com.jecyhw.model.subModel.Picture;

import java.io.InputStream;

/**
 * Created by jecyhw on 16-9-14.
 */
@Deprecated
public interface CustomFSFileRepository {

    DeepZoomMetaData deepZoomFile(String fileId);
    void deepZoomFile(Picture picture);
    InputStream picture(String id);
}
