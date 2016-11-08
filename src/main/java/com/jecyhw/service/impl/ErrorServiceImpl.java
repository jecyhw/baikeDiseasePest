package com.jecyhw.service.impl;

import com.jecyhw.document.SearchError;
import com.jecyhw.repository.ErrorRepository;
import com.jecyhw.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jecyhw on 16-8-29.
 */

@Deprecated
@Service
final public class ErrorServiceImpl implements ErrorService{
    @Autowired
    ErrorRepository repository;

    @Override
    public void save(SearchError searchError) {
        repository.save(searchError);
    }
}
