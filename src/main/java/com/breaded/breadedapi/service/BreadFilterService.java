package com.breaded.breadedapi.service;

import java.util.List;

import com.breaded.breadedapi.entity.BreadFilter;

public interface BreadFilterService {
	
	List<BreadFilter> findAll();
	
	List<BreadFilter> saveAll(List<BreadFilter>  breadfilterList);	

}
