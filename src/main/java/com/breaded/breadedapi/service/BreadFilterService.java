package com.breaded.breadedapi.service;

import java.util.List;

import com.breaded.breadedapi.entity.BreadFilter;

public interface BreadFilterService {
	
	List<BreadFilter> findAll();
	
	BreadFilter save(BreadFilter breadfilter);	

}
