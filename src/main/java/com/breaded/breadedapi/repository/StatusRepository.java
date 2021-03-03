package com.breaded.breadedapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.breaded.breadedapi.entity.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {

}
