package com.breaded.breadedapi.service;

import java.util.Optional;

import com.breaded.breadedapi.entity.User;

public interface UserService {

	Optional<User> findByEmailAndPassword(String email, String password);
	
	User Save(User user);
}
