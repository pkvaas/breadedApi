package com.breaded.breadedapi.service;

import java.util.List;

import com.breaded.breadedapi.entity.Subscription;

public interface SubscriptionService {

	List<Subscription> findAll();
	
	Subscription save(Subscription subscription);
}
