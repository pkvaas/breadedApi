package com.breaded.breadedapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.breaded.breadedapi.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
