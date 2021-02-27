package com.breaded.breadedapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.breaded.breadedapi.entity.Breads;

@Repository
public interface BreadsRepository extends CrudRepository<Breads, Long> {

}
