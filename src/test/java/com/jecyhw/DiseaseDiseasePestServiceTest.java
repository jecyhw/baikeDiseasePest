package com.jecyhw;

import com.jecyhw.service.DiseasePestService;
import org.junit.runner.RunWith;
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

    @Autowired
    DiseasePestService service;

    @org.junit.Test
    public void test() throws IOException {
        service.linkToPicture();
//        Response response = RequestUtil.getResponse("http://a.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=0a69d9c0572c11dfcadcb771024e09b5/ae51f3deb48f8c54fc5edb0c3a292df5e0fe7f07.jpg");
//        FileUtils.copyInputStreamToFile(response.body().byteStream(), new File("1.jpg"));
    }
}
