package com.jecyhw.service;

import com.jecyhw.model.document.DiseasePest;

import java.io.IOException;
import java.util.List;

/**
 * Created by jecyhw on 16-9-6.
 */
public interface DiseasePestService {
    DiseasePest search(String word, DiseasePest.Type type) throws IOException;

    List<DiseasePest> all();
}
