package com.jecyhw.controller;

import com.jecyhw.core.Freemarker;
import com.jecyhw.core.response.Response;
import com.jecyhw.core.upload.PictureFile;
import com.jecyhw.core.validation.MessageCodeResolver;
import com.jecyhw.model.database.BaseModel;
import com.jecyhw.model.subModel.Picture;
import com.jecyhw.model.subModel.Source;
import com.jecyhw.service.PictureService;
import com.jecyhw.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by jecyhw on 16-11-6.
 */
abstract class BaseController<T extends BaseModel> {

    final static String DISEASE = "disease";
    final static String PEST = "pest";

    @Autowired
    PictureService pictureService;

    @Autowired
    Freemarker freemarker;

    @Autowired
    MessageCodeResolver messageCodeResolver;

    abstract BaseService<T> getService();

    /**
     * 控制器的所属类型,是disease还是pest
     * @return
     */
    abstract String getType();

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    private ModelAndView modelAndView(String view) {
        return new ModelAndView(addTypeAtStringPrefix("/" + view), "type", getType());
    }

    public ModelAndView home() {
        return modelAndView("show");
    }

    public ModelAndView pivotViewer() {
        return modelAndView("pivotViewer");
    }

    public ModelAndView createGet(T entity) {
        ModelAndView result = modelAndView("create");
        result.addObject(getType(), entity);
        result.addObject("pictureFile", new PictureFile());
        return result;
    }

    public Response<?> uploadFile(String id, PictureFile pictureFile) {
        T entity = getService().findById(id);
        if (entity == null) {
            return Response.notFound("未找到图片的所属记录");
        }
        Picture picture =  pictureService.create(pictureFile);
        if (picture == null) {
            return Response.fail("图片保存失败");
        }
        List<Picture> pictures = entity.getPictures();
        if (pictures == null) {
            pictures = new ArrayList<>();
            entity.setPictures(pictures);
        }
        pictures.add(picture);
        getService().save(entity);
        return Response.success(picture);
    }

    public Response<?> createPost(T entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Response.createFail(messageCodeResolver.bindingResultToSimple(bindingResult));
        }
        entity.setSource(Source.HAND);
        getService().save(entity);
        return Response.success(entity);
    }

    public Response<?> deletePicture(String id, String fileId) throws IOException {
        T entity = getService().findById(id);
        if (entity != null) {
            List<Picture> pictures = entity.getPictures();
            Picture picture;
            if (pictures != null) {
                for (int i = 0; i < pictures.size(); i++) {
                    picture = pictures.get(i);
                    if (picture.getFileId().equals(fileId)) {
                        pictures.remove(i);
                        getService().save(entity);
                        return Response.success("记录包含的图片删除成功");
                    }
                }
            }
            return Response.notFound("未找到图片的所属记录");
        }
        return Response.notFound("未找到记录");
    }

    public Response<?> delete(String id) {
        getService().deleteById(id);
        return Response.success("删除成功");
    }

    public Response<?> queryByName(String name) {
        T entity = getService().findByChineseName(name);
        if (entity == null) {
            return Response.notFound("未找到记录");
        }
        return Response.success(entity);
    }

    public ResponseEntity<?> deepZoomConfig() {
        Map<String, Object> root = new Hashtable<>();
        root.put("deepZoomFiles", getService().extractDeepZoomField());
        return ResponseEntity.ok().body(freemarker.process("deepZoomConfig.ftl", root));
    }

    /**
     * @return
     */
    public ResponseEntity<?> pivotViewerConfig() {
        Map<String, Object> root = new Hashtable<>();
        root.put(addTypeAtStringPrefix("s"), getService().picturesSizeNotEmpty());
        return ResponseEntity.ok().body(freemarker.process(addTypeAtStringPrefix("/pivotViewerConfig.ftl"), root));
    }

    public Response<?> pictures(String diseaseId) {
        T entity = getService().findById(diseaseId);
        if (entity != null) {
            return Response.success(entity.getPictures());
        } else {
            return Response.notFound("未找到记录");
        }
    }

    /**
     * @param value
     * @return 给字符串添加类型前缀
     */
    private String addTypeAtStringPrefix(String value) {
        return getType() + value;
    }
}
