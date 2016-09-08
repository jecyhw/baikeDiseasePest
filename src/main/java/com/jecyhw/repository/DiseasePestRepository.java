package com.jecyhw.repository;

import com.jecyhw.document.DiseasePest;
import com.jecyhw.repository.custom.DiseasePestTransformRepository;
import com.jecyhw.repository.custom.DocumentFieldRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jecyhw on 16-8-26.
 */
public interface DiseasePestRepository extends CrudRepository<DiseasePest, String>, DiseasePestTransformRepository, DocumentFieldRepository<DiseasePest> {
    DiseasePest findByName(String name);
    List<DiseasePest> findByType(DiseasePest.Type type);
}
