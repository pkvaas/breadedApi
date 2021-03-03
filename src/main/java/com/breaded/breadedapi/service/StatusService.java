package com.breaded.breadedapi.service;

import java.util.List;

import com.breaded.breadedapi.entity.Status;

public interface StatusService {
	
	Status save(Status status);
	
	List<Status> findAll();

}
