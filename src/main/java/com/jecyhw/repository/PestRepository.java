package com.jecyhw.repository;

import com.jecyhw.model.database.Pest;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jecyhw on 16-9-6.
 */
public interface PestRepository  extends CrudRepository<Pest, String> {
}
