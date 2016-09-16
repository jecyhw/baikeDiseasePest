package com.jecyhw.service.impl;

import com.google.common.collect.ImmutableList;
import com.jecyhw.model.database.FSFile;
import com.jecyhw.model.database.Pest;
import com.jecyhw.repository.FSFileRepository;
import com.jecyhw.repository.PestRepository;
import com.jecyhw.service.PestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jecyhw on 16-9-8.
 */
@Service
public class PestServiceImpl implements PestService{

    @Autowired
    PestRepository pestRepository;

    @Autowired
    FSFileRepository fsFileRepository;

    @Autowired
    GridFsOperations gridFsOperations;

    @Override
    public void save(List<Pest> pests) {
        pestRepository.save(pests);
    }

    @Override
    public List<FSFile> deepZoomFiles() {
        List<FSFile> files = ImmutableList.copyOf(fsFileRepository.findAll());
        for (FSFile file : files) {
            fsFileRepository.deepZoomFile(file);
        }
        return files;
    }

    @Override
    public List<Pest> pivotViewer() {
        return pestRepository.findByPicturesSizeNotEmpty();
    }
}
