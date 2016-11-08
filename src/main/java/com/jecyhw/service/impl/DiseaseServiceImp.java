package com.jecyhw.service.impl;

import com.jecyhw.model.database.Disease;
import com.jecyhw.repository.BaseOperations;
import com.jecyhw.repository.DiseaseRepository;
import com.jecyhw.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jecyhw on 16-11-5.
 */
@Service
public class DiseaseServiceImp extends BaseService<Disease> implements DiseaseService{

    @Autowired
    DiseaseRepository diseaseRepository;

    @Override
    protected BaseOperations<Disease> getRepository() {
        return diseaseRepository;
    }
}
