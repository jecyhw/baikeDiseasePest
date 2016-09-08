package com.jecyhw;

import com.jecyhw.service.DiseasePestService;
import com.jecyhw.service.PestService;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void test() throws IOException {
        diseasePestService.transformPictures();
        pestService.save(diseasePestService.transformPest());
    }
}
