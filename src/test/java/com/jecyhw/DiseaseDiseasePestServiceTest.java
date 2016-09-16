package com.jecyhw;

import com.google.common.collect.ImmutableList;
import com.jecyhw.model.database.Pest;
import com.jecyhw.repository.PestRepository;
import com.jecyhw.service.DiseasePestService;
import com.jecyhw.service.PestService;
import com.jecyhw.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

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

    @Test
    public void test() throws IOException {
        List<Pest> pests = ImmutableList.copyOf(pestRepository.findAll());
        System.out.println("----------------------------");
        for (Pest pest : pests) {
            pest.setKingdom(update(pest.getKingdom()));
            pest.setPhylum(update(pest.getPhylum()));
            pest.setSubPhylum(update(pest.getSubPhylum()));
            pest.setPestClass(update(pest.getPestClass()));
            pest.setPestSubClass(update(pest.getPestSubClass()));
            pest.setOrder(update(pest.getOrder()));
            pest.setSubOrder(update(pest.getSubOrder()));
            pest.setFamily(update(pest.getFamily()));
            pest.setSubFamily(update(pest.getSubFamily()));
            pest.setGenus(update(pest.getGenus()));
            pest.setSubGenus(update(pest.getSubGenus()));
            //pestRepository.save(pest);
        }
        System.out.println("----------------------------");
    }

    private String update(String value) {
        if (value == null) {
            return value;
        }
        String result = StringUtil.removeBlank(value);
        if (!result.matches("^[a-zA-Z0-9(（)）]*$")) {
            result = result.replaceAll("[a-zA-Z0-9(（)）]", "");
        }
        return result;
    }
}
