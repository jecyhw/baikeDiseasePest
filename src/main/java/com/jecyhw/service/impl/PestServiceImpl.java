package com.jecyhw.service.impl;

import com.jecyhw.model.database.Pest;
import com.jecyhw.repository.PestRepository;
import com.jecyhw.service.PestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jecyhw on 16-9-8.
 */
@Service
public class PestServiceImpl implements PestService{

    @Autowired
    PestRepository pestRepository;

    @Override
    public void save(List<Pest> pests) {
        pestRepository.save(pests);
    }
}
