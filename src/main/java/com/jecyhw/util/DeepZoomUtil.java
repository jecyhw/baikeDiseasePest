package com.jecyhw.util;

import com.jecyhw.model.database.FSFile;
import gov.nist.isg.archiver.DirectoryArchiver;
import gov.nist.isg.archiver.FilesArchiver;
import gov.nist.isg.pyramidio.BufferedImageReader;
import gov.nist.isg.pyramidio.PartialImageReader;
import gov.nist.isg.pyramidio.ScalablePyramidBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jecyhw on 16-9-12.
 */
final public class DeepZoomUtil {

    static final String ROOTPATH = "/var/diseasePest";

    static public boolean directoryExists(String directoryName) {
        File file = new File(Paths.get(ROOTPATH, directoryName).toString());
        return file.exists() && file.isDirectory();
    }

    static public boolean fileExists(String fileName) {
        File file = new File(Paths.get(ROOTPATH, fileName).toString());
        return file.exists() && file.isFile();
    }

    static public FSFile.MetaData buildPyramid(InputStream inputStream, String directoryName) throws IOException {
        File file = Paths.get(ROOTPATH, directoryName).toFile();
        FileUtils.copyInputStreamToFile(inputStream, file);
        ScalablePyramidBuilder spb = new ScalablePyramidBuilder(256, 1, "png", "xml");
        FilesArchiver archiver = new DirectoryArchiver(new File(ROOTPATH));
        PartialImageReader pir = new BufferedImageReader(file);
        spb.buildPyramid(pir, directoryName, archiver);
        Map<String, Integer> map = new HashMap<>();
        FSFile.MetaData metaData = new FSFile.MetaData();
        metaData.setHeight(pir.getHeight());
        metaData.setWidth(pir.getWidth());
        return metaData;
    }
}
