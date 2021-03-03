package com.breaded.breadedapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.breaded.breadedapi.entity.Myboxes;
import com.breaded.breadedapi.entity.User;

@Repository
public interface MyBoxesRepository extends CrudRepository<Myboxes, Long> {
	
	List<Myboxes> findByUser(User user);

}
