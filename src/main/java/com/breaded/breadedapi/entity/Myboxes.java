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
@Table(name = "myboxes")
@Data
public class Myboxes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="box_id")
	private long id;
	
	@Column(name = "box_number")
	private String boxNumber;
	
	@Column(name = "dateofdelivery")
	private String dateofdelivery;
	
	@Column(name = "timeofdelivery")
	private String timeofdelivery;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id", referencedColumnName = "status_id")
	private Status status;

}
