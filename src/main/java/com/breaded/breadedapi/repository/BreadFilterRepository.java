package com.breaded.breadedapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.breaded.breadedapi.entity.BreadFilter;

@Repository
public interface BreadFilterRepository extends CrudRepository<BreadFilter, Long> {

}
