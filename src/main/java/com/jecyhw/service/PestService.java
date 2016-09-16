package com.jecyhw.service;

import com.jecyhw.model.database.FSFile;
import com.jecyhw.model.database.Pest;

import java.util.List;

/**
 * Created by jecyhw on 16-9-8.
 */
public interface PestService {
    void save(List<Pest> pests);

    List<FSFile> deepZoomFiles();

    List<Pest> pivotViewer();
}
