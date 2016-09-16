package com.jecyhw.repository;

import com.jecyhw.model.database.FSFile;
import com.jecyhw.repository.custom.CustomFSFileRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jecyhw on 16-9-12.
 */
public interface FSFileRepository extends CrudRepository<FSFile, String>, CustomFSFileRepository {
}
