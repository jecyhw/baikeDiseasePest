package com.jecyhw.transform;

import com.jecyhw.model.document.DiseasePest;
import com.jecyhw.model.document.Picture;

import java.util.List;

/**
 * Created by jecyhw on 16-9-6.
 * 将百科病虫害的图片链接下载并进行转换
 */
public interface DiseasePestPictureTransform extends DiseasePestTransform<DiseasePest>{
    List<Picture> pictures();
}
