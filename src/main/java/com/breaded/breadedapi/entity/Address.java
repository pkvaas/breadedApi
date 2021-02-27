package com.breaded.breadedapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "address", schema = "breaded")
@Data

@EqualsAndHashCode(exclude = "user")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private long id;

	@Column(name = "addressline_1")
	private String addressline1;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "city_name")
	private String cityname;
	
	
	@Column(name = "post_code")
	private String postcode;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	@JsonIgnoreProperties("addresses")
	private User user;

}
