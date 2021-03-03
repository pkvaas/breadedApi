package com.breaded.breadedapi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Entity
@Table(name = "boxedbreads")
@Data
public class BoxedBreads {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="boxedbreads_id")
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bread_id", referencedColumnName = "bread_id")
	private Breads breads;
	
	@Column(name="count")
	private int count;
	
	@ManyToOne
	@JoinColumn(name="myboxes_id", nullable=false)
	@JsonIgnoreProperties("boxedBreadList")
	private Myboxes myboxes;
	
	
}
