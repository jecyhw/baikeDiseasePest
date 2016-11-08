package com.jecyhw.controller;

import com.jecyhw.core.response.Response;
import com.jecyhw.service.PictureService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jecyhw on 16-11-3.
 */
@RestController
@RequestMapping(value = "picture")
public class PictureController {

    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public Response<?> delete(@PathVariable("id") String fileId) {
        pictureService.delete(fileId);
        return Response.success("删除图片文件成功");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> picture(@PathVariable("id") String fileId) throws IOException {
        InputStream in = pictureService.findByFileIdAsInputStream(fileId);
        if (in != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(IOUtils.toByteArray(in));
        }
        return new ResponseEntity<>("未找到图片", HttpStatus.NOT_FOUND);
    }
}
