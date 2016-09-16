package com.jecyhw.repository;

import com.jecyhw.model.database.Pest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jecyhw on 16-9-6.
 */
public interface PestRepository  extends CrudRepository<Pest, String> {

    @Query("{ $where : 'this.pictures.length > 0'}")
    List<Pest> findByPicturesSizeNotEmpty();
}
