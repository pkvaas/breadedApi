package com.breaded.breadedapi.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "breads")
@Data
public class Breads {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bread_id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "flavours")
	private String flavours;
	
	@Column(name = "imagepath")
	private String imagepath;
	
	@Column(name = "vegan")
	private Boolean vegan;
	
	@Column(name = "gluten")
	private Boolean gluten;
	
	@OneToMany
	@JoinTable(name ="breads_breadfilter" ,joinColumns = {@JoinColumn(name="bread_id", referencedColumnName = "bread_id")}, 
	inverseJoinColumns = {@JoinColumn(name="filter_id", referencedColumnName = "filter_id")} )
	private Set<BreadFilter> breadFilters;
	
	
}
