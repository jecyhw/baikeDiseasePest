package com.jecyhw.transform;

import com.jecyhw.model.document.DiseasePest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jecyhw on 16-9-6.
 * 抓取的百科病虫害数据转成自己对应的的数据库字段
 */
public interface DiseasePestTransform<T> {
    T transform(DiseasePest diseasePest);

    default List<T> transforms(List<DiseasePest> diseasePests) {
        List<T> list = new ArrayList<>();
        for (DiseasePest diseasePest : diseasePests) {
            list.add(transform(diseasePest));
        }
        return list;
    }
}
