package com.jecyhw.repository.transform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jecyhw on 16-9-6.
 * 抓取保存到mongodb数据库的病虫害document转成自己对应的的document
 * @param <S> S表示抓取到的病虫害document对应的Java对象
 * @param <D> D表示要转成的document对应的Java对象
 */
public interface Transform<S, D> {
    D transform(S diseasePest);

    default List<D> transforms(List<S> diseasePests) {
        List<D> list = new ArrayList<>();
        for (S diseasePest : diseasePests) {
            list.add(transform(diseasePest));
        }
        return list;
    }
}
