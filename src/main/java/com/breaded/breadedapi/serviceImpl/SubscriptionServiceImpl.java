package com.breaded.breadedapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breaded.breadedapi.entity.Subscription;
import com.breaded.breadedapi.repository.SubscriptionRepository;
import com.breaded.breadedapi.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Override
	public List<Subscription> findAll() {
		return (List<Subscription>) subscriptionRepository.findAll();
	}

	@Override
	public Subscription save(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

}
