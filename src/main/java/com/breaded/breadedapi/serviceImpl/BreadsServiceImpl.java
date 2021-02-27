package com.breaded.breadedapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breaded.breadedapi.entity.Breads;
import com.breaded.breadedapi.repository.BreadsRepository;
import com.breaded.breadedapi.service.BreadsService;

@Service
public class BreadsServiceImpl implements BreadsService {

	@Autowired
	BreadsRepository breadsRepository;
	
	@Override
	public List<Breads> findAll() {
		return (List<Breads>) breadsRepository.findAll();
	}

	@Override
	public Breads save(Breads bread) {
		return breadsRepository.save(bread);
	}

}
