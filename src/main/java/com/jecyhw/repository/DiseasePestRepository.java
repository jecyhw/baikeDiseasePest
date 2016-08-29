package com.jecyhw.repository;

import com.jecyhw.document.DiseasePest;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jecyhw on 16-8-26.
 */
public interface DiseasePestRepository extends CrudRepository<DiseasePest, String> {
    DiseasePest findByName(String name);
}
