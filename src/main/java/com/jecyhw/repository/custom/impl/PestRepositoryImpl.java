package com.jecyhw.repository.custom.impl;

import com.jecyhw.repository.custom.CustomPestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by jecyhw on 16-9-12.
 */
public class PestRepositoryImpl implements CustomPestRepository {

    @Autowired
    MongoOperations operations;
}
