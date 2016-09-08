package com.jecyhw.controller;

import com.jecyhw.document.DiseasePest;
import com.jecyhw.document.SearchError;
import com.jecyhw.response.Response;
import com.jecyhw.service.DiseasePestService;
import com.jecyhw.service.ErrorService;
import com.jecyhw.util.Freemarker;
import com.jecyhw.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

/**
 * Created by jecyhw on 16-8-25.
 */
@RestController
public class DiseasePestController {

    @Autowired
    DiseasePestService diseasePestService;

    @Autowired
    ErrorService errorService;


    @RequestMapping(value = "")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "pestSearch", method = RequestMethod.POST)
    public Response<?> baikePestSearch(@RequestParam String pestName ) {
        return baikeSearch(pestName, DiseasePest.Type.PEST);
    }

    @RequestMapping(value = "diseaseSearch", method = RequestMethod.POST)
    public Response<?> baikeDiseaseSearch(@RequestParam String diseaseName ) {
        return baikeSearch(diseaseName, DiseasePest.Type.DISEASE);
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ModelAndView all() {
        return new ModelAndView("all", "diseasePests", diseasePestService.all());

    }

    private Response<?> baikeSearch(String name, DiseasePest.Type type) {
        List<Object> diseasePests = new ArrayList<>();
        for (String word : split(name)) {
            try {
                diseasePests.add(diseasePestService.search(word, type));
            } catch (IOException e) {
                SearchError error = new SearchError(word, e.getMessage());
                errorService.save(error);
                diseasePests.add(error);
            }
        }

        Map<String, Object> root = new Hashtable<>();
        root.put("diseasePests", diseasePests);
        return Response.success(Freemarker.process("search.ftl", root));

    }

    private List<String> split(String name) {
        String filter = StringUtil.replaceBlank(name, "|");
        filter = filter.replaceAll("\\|\\|+", "|");
        filter = StringUtils.removeStart(filter, "|");
        filter = StringUtils.removeEnd(filter, "|");
        if (filter.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(filter.split("\\|"));
    }

}
