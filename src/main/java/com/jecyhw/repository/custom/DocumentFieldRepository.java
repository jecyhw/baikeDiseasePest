package com.jecyhw.repository.custom;

import java.util.List;
import java.util.Set;

/**
 * Created by jecyhw on 16-9-7.
 */
public interface DocumentFieldRepository<T> {
    /**
     * 获取allData中的所有字段
     * @param allData
     * @return
     */
    Set<String> collectionFields(List<T> allData);

    /**
     * 获取collection中的所有字段
     * @return
     */
    Set<String> collectionFields();
}
