package com.jecyhw.controller;

import com.jecyhw.document.DiseasePest;
import com.jecyhw.document.SearchError;
import com.jecyhw.model.database.Pest;
import com.jecyhw.response.Response;
import com.jecyhw.service.DiseasePestService;
import com.jecyhw.service.ErrorService;
import com.jecyhw.service.PestService;
import com.jecyhw.util.Freemarker;
import com.jecyhw.util.StringUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by jecyhw on 16-8-25.
 */
@RestController
public class DiseasePestController {
    @Autowired
    DiseasePestService diseasePestService;

    @Autowired
    PestService pestService;

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

    @RequestMapping(value = "pestDeepZoomFiles.xml", method = RequestMethod.GET)
    public String pestDeepZoomFiles() {
        Map<String, Object> root = new Hashtable<>();
        root.put("deepZoomFiles", pestService.deepZoomFiles());
        return Freemarker.process("pestDeepZoomFiles.ftl", root);
    }

    @RequestMapping(value = "pestPivotViewer.cxml", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public String pestPivotViewer() {
        Map<String, Object> root = new Hashtable<>();
        root.put("pests", pestService.pivotViewer());
        return Freemarker.process("pestPivotViewer.ftl", root);
    }

    @RequestMapping(value = "pivotviewer", method = RequestMethod.GET)
    public ModelAndView pivotViewer() {
        return new ModelAndView("pivotviewer");
    }

    @RequestMapping(value = "morePicture/{id}", method = RequestMethod.GET)
    public Response<?> morePictures(@PathVariable String id) {
        Pest pest = pestService.findById(id);
        if (pest != null) {
            return Response.success(pestService.findById(id));
        } else {
            return Response.fail("未查找到相关记录");
        }
    }

    @RequestMapping(value = "picture/{id}")
    @ResponseBody
    public byte[] picture(@PathVariable String id) throws IOException {
        InputStream in = pestService.picture(id);
        if (in != null) {
            return IOUtils.toByteArray(in);
        }
        return new byte[0];
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
