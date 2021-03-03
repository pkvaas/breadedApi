package com.breaded.breadedapi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "breads")
@Data
@EqualsAndHashCode(exclude = "breadFilters")
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
	
//	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinTable(name ="breads_breadfilter" ,joinColumns = {@JoinColumn(name="bread_id", referencedColumnName = "bread_id")}, 
//	inverseJoinColumns = {@JoinColumn(name="filter_id", referencedColumnName = "filter_id")} )
//	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "breads_breadfilter", 
    joinColumns = { @JoinColumn(name = "bread_id", nullable = false) }, 
    inverseJoinColumns = { @JoinColumn(name = "filter_id", 
            nullable = false) })
	@JsonIgnoreProperties("breadlist")
	private Set<BreadFilter> breadFilters;
	
}
