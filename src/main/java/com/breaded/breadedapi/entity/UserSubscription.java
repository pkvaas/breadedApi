package com.breaded.breadedapi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "usersubscription")
@Data
public class UserSubscription {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usersubscription_id")
	private long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subscription_id", referencedColumnName = "subscription_id")
	private Subscription subscription;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	

}
