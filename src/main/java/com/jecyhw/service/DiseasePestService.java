package com.jecyhw.service;

import com.jecyhw.model.database.Pest;
import com.jecyhw.document.DiseasePest;
import com.jecyhw.repository.custom.DocumentFieldRepository;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by jecyhw on 16-9-6.
 */
@Deprecated
public interface DiseasePestService extends DocumentFieldRepository<DiseasePest>{

    DiseasePest search(String word, DiseasePest.Type type) throws IOException;

    List<DiseasePest> all();

    /**
     * 抓取到的百科病虫害DiseasePest的图片保存到mongodb,并更新DiseasePest
     */
    void transformPictures();

    /**
     * 抓渠到的百科病虫害DiseasePest中的虫害转换成自己数据格式的Pest
     */
    List<Pest> transformPest();

    Set<String> collectionFields(DiseasePest.Type type);
}
