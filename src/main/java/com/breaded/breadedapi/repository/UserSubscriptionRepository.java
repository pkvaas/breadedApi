package com.breaded.breadedapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.breaded.breadedapi.entity.UserSubscription;

@Repository
public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {

}
