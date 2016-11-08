package com.jecyhw;

import com.jecyhw.core.response.Code;
import com.jecyhw.repository.PestRepository;
import com.jecyhw.service.DiseasePestService;
import com.jecyhw.service.PestService;
import com.jecyhw.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by jecyhw on 16-8-26.
 // */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/resources/dispatcher-servlet.xml" })
public class DiseaseDiseasePestServiceTest {

    Logger logger = LoggerFactory.getLogger(DiseaseDiseasePestServiceTest.class);

    @Autowired
    DiseasePestService diseasePestService;

    @Autowired
    PestService pestService;

    @Autowired
    PestRepository pestRepository;

    @Autowired
    GridFsOperations gridFsOperations;

    @Test
    public void test() throws IOException {
//        List<Pest> pests = ImmutableList.copyOf(pestRepository.findAll());
//        System.out.println("----------------------------");
//         fsFileRepository.findAll();
//        for (Pest pest : pests) {
//            List<Picture> pictures = pest.getPictures();
//            if (pictures != null && pictures.size() > 0) {
//                Picture picture = pictures.get(0);
//
//                GridFSDBFile fsdbFile = gridFsOperations.findOne(Query.query(Criteria.where("_id").is(picture.getFileId())));
//                if (fsdbFile != null) {
//                    BufferedImage bufferedImage = ImageIO.read(fsdbFile.getInputStream());
//                    DeepZoomMetaData deepZoomMetaData = new DeepZoomMetaData();
//                    deepZoomMetaData.setFileId(picture.getFileId());
//                    deepZoomMetaData.setHeight(bufferedImage.getHeight());
//                    deepZoomMetaData.setWidth(bufferedImage.getWidth());
//                    pest.setDeepZoom(deepZoomMetaData);
//                }
//            }
//            //pestRepository.save(pest);
//        }
//        System.out.println("----------------------------");
        System.out.println(JsonUtil.valueAsString(Code.CREATE_FAIL));
        System.out.println(Code.CREATE_FAIL);
    }
}
