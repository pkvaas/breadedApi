package com.breaded.breadedapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.breaded.breadedapi.entity.Myboxes;
import com.breaded.breadedapi.entity.User;

public interface MyBoxesRepository extends CrudRepository<Myboxes, Long> {
	
	List<Myboxes> findByUser(User user);

}
