package com.breaded.breadedapi.service;

import java.util.List;

import com.breaded.breadedapi.entity.Breads;

public interface BreadsService {
	
	List<Breads> findAll();
	
	List<Breads> saveAll(List<Breads> bread);
	
}
