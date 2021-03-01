package com.breaded.breadedapi.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "breadfilter")
@Data
@EqualsAndHashCode(exclude = "breadlist")
@JsonIgnoreProperties("breadFilters")
public class BreadFilter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="filter_id")
	private long id;

	@Column(name = "name")
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "breadFilters")
	private Set<Breads> breadlist;
}
