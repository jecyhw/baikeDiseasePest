package com.jecyhw.service;

import com.jecyhw.document.SearchError;
import com.jecyhw.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jecyhw on 16-8-29.
 */
@Service
public class ErrorService {
    @Autowired
    ErrorRepository repository;

    public void save(SearchError searchError) {
        repository.save(searchError);
    }
}
