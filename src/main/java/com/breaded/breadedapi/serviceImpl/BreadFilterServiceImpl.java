package com.breaded.breadedapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breaded.breadedapi.entity.BreadFilter;
import com.breaded.breadedapi.repository.BreadFilterRepository;
import com.breaded.breadedapi.service.BreadFilterService;

@Service
public class BreadFilterServiceImpl implements BreadFilterService {

	@Autowired
	BreadFilterRepository breadFilterRepository;
	
	@Override
	public List<BreadFilter> findAll() {
		return (List<BreadFilter>) breadFilterRepository.findAll();
	}

	@Override
	public List<BreadFilter> saveAll(List<BreadFilter> breadfilterList) {
		return (List<BreadFilter>) breadFilterRepository.saveAll(breadfilterList);
	}

}
