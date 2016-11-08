package com.jecyhw.core.pivotviewer;

import com.jecyhw.core.file.GridFsFile;
import com.jecyhw.model.subModel.DeepZoomMetaData;
import gov.nist.isg.archiver.DirectoryArchiver;
import gov.nist.isg.archiver.FilesArchiver;
import gov.nist.isg.pyramidio.BufferedImageReader;
import gov.nist.isg.pyramidio.PartialImageReader;
import gov.nist.isg.pyramidio.ScalablePyramidBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * Created by jecyhw on 16-11-1.
 */
public class DeepZoomFileTemplate {

    @Autowired
    private GridFsFile fsFile;

    private final String rootPath;
    private final int tileSize;
    private final int overlap;
    private final String tileFormat;
    private final String descriptorExt;

    @PostConstruct
    public void initMethod() {
        File rootDirectory = new File(rootPath);
        if (!rootDirectory.exists()) {
            rootDirectory.mkdirs();
        }
    }

    /**
     *
     * @param rootPath deepZoomFile文件的根目录
     * @param tileSize  图片切片的大小
     * @param overlap 图片切片间重叠宽度
     * @param tileFormat 图片切片的格式
     * @param descriptorExt 图片切片说明文件的格式
     */
    public DeepZoomFileTemplate(String rootPath, int tileSize, int overlap, String tileFormat, String descriptorExt) {
        this.rootPath = rootPath;
        this.tileSize = tileSize;
        this.overlap = overlap;
        this.tileFormat = tileFormat;
        this.descriptorExt = descriptorExt;
    }

    public boolean directoryExists(String directoryName) {
        File file = new File(Paths.get(rootPath, directoryName).toString());
        return file.exists() && file.isDirectory();
    }

    public boolean fileExists(String fileName) {
        File file = new File(Paths.get(rootPath, fileName).toString());
        return file.exists() && file.isFile();
    }

    /**
     * 对图片进行切图生成层级图片
     * @param fileId
     */
    public DeepZoomMetaData createDeepZoom(String fileId) {
        InputStream inputStream = fsFile.findByFileIdAsInputStream(fileId);
        if (inputStream != null) {
            try {
                File file = Paths.get(rootPath, fileId).toFile();
                FileUtils.copyInputStreamToFile(inputStream, file);
                ScalablePyramidBuilder spb = new ScalablePyramidBuilder(tileSize, overlap, tileFormat, descriptorExt);
                FilesArchiver archiver = new DirectoryArchiver(new File(rootPath));
                PartialImageReader pir = new BufferedImageReader(file);
                spb.buildPyramid(pir, fileId, archiver);
                return getPictureDeepZoomMetaData(pir, fileId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void deleteDeepZoom(String fileId) {
        FileUtils.deleteQuietly(new File(fileId + "_files"));
        FileUtils.deleteQuietly(new File(fileId));
        FileUtils.deleteQuietly(new File(fileId + descriptorExt));
    }

    private DeepZoomMetaData getPictureDeepZoomMetaData(PartialImageReader imageReader, String fileId)
    {
        DeepZoomMetaData deepZoomMetaData = new DeepZoomMetaData();
        deepZoomMetaData.setFileId(fileId);
        deepZoomMetaData.setHeight(imageReader.getHeight());
        deepZoomMetaData.setWidth(imageReader.getWidth());
        return deepZoomMetaData;
    }
}
