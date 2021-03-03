package com.breaded.breadedapi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.breaded.breadedapi.entity.UserSubscription;
import com.breaded.breadedapi.repository.UserSubscriptionRepository;
import com.breaded.breadedapi.service.UserSubscriptionService;

public class UserSubscriptionServiceImpl implements UserSubscriptionService {

	@Autowired
	UserSubscriptionRepository userSubscriptionRepository;
	
	@Override
	public UserSubscription save(UserSubscription userSubscription) {
		return userSubscriptionRepository.save(userSubscription);
	}

}
