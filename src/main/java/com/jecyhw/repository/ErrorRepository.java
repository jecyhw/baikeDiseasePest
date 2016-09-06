package com.jecyhw.repository;

import com.jecyhw.model.document.SearchError;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jecyhw on 16-8-29.
 */
public interface ErrorRepository  extends CrudRepository<SearchError, String> {
}
