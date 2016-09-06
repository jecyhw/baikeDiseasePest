package com.jecyhw.repository;

import com.jecyhw.model.document.DiseasePest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jecyhw on 16-8-26.
 */
public interface DiseasePestRepository extends CrudRepository<DiseasePest, String> {
    DiseasePest findByName(String name);
    List<DiseasePest> findByType(DiseasePest.Type type);
}
