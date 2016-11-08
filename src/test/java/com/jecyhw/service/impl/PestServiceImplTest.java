package com.jecyhw.service.impl;

import com.jecyhw.service.PestService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * PestServiceImpl Tester.
 *
 * @author <Authors name>
 * @since <pre>九月 12, 2016</pre>
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml", "file:src/main/resources/dispatcher-servlet.xml" })
public class PestServiceImplTest {

    @Autowired
    PestService pestService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: save(List<Pest> pests)
     *
     */
    @Test
    public void testSave() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: findByPicturesExists()
     *
     */
    @Test
    public void testFindByPicturesIsNotNull() throws Exception {
        System.out.println(pestService.extractDeepZoomField());
//TODO: Test goes here...
    }


}
