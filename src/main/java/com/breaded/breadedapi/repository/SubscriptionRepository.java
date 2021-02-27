package com.breaded.breadedapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.breaded.breadedapi.entity.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

}
