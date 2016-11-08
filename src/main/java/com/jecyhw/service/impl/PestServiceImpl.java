package com.jecyhw.service.impl;

import com.jecyhw.model.database.Pest;
import com.jecyhw.repository.BaseOperations;
import com.jecyhw.repository.PestRepository;
import com.jecyhw.service.PestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jecyhw on 16-9-8.
 */
@Service
public class PestServiceImpl extends BaseService<Pest> implements PestService{

    @Autowired
    PestRepository pestRepository;

    @Override
    protected BaseOperations<Pest> getRepository() {
        return pestRepository;
    }
}
