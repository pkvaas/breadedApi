package com.breaded.breadedapi.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breaded.breadedapi.entity.User;
import com.breaded.breadedapi.repository.UserRepository;
import com.breaded.breadedapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User Save(User user) {
		return userRepository.save(user);
	}

}
