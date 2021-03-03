package com.breaded.breadedapi.service;

import java.util.List;
import java.util.Optional;

import com.breaded.breadedapi.entity.User;

public interface UserService {
	
	List<User> findAll(); 

	Optional<User> findById(Long id);
	
	Optional<User> findByEmailAndPassword(String email, String password);
	
	User Save(User user);
}
