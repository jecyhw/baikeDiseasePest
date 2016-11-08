package com.jecyhw.controller;

import com.jecyhw.service.DiseasePestService;
import com.jecyhw.service.ErrorService;
import com.jecyhw.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jecyhw on 16-8-25.
 */
@Deprecated
@RestController
public class DiseasePestController {
    @Autowired
    DiseasePestService diseasePestService;

    @Autowired
    ErrorService errorService;

//    @RequestMapping(value = "")
//    public ModelAndView index() {
//        return new ModelAndView("index");
//    }
//
//    @RequestMapping(value = "pestSearch", method = RequestMethod.POST)
//    public Response<?> baikePestSearch(@RequestParam String pestName ) {
//        return baikeSearch(pestName, DiseasePest.Type.PEST);
//    }
//
//    @RequestMapping(value = "diseaseSearch", method = RequestMethod.POST)
//    public Response<?> baikeDiseaseSearch(@RequestParam String diseaseName ) {
//        return baikeSearch(diseaseName, DiseasePest.Type.DISEASE);
//    }
//
//    @RequestMapping(value = "all", method = RequestMethod.GET)
//    public ModelAndView all() {
//        return new ModelAndView("all", "diseasePests", diseasePestService.all());
//    }
//
//    private Response<?> baikeSearch(String name, DiseasePest.Type type) {
//        List<Object> diseasePests = new ArrayList<>();
//        for (String word : split(name)) {
//            try {
//                diseasePests.add(diseasePestService.search(word, type));
//            } catch (IOException e) {
//                SearchError error = new SearchError(word, e.getMessage());
//                errorService.save(error);
//                diseasePests.add(error);
//            }
//        }
//
//        Map<String, Object> root = new Hashtable<>();
//        root.put("diseasePests", diseasePests);
//        return Response.success(Freemarker.process("search.ftl", root));
//    }

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
