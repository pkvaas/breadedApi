package com.breaded.breadedapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "breadfilter", schema = "breaded")
@Data
public class BreadFilter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="filter_id")
	private long id;

	@Column(name = "name")
	private String name;

}
