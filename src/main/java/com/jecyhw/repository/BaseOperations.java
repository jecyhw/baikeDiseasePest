package com.jecyhw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by jecyhw on 16-11-5.
 */
@NoRepositoryBean
public interface BaseOperations<T> extends MongoRepository<T, String>{
    List<T> findByPicturesExists(boolean exists);

    T findByChineseName(String chineseName);
}
