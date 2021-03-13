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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "myboxes")
@Data
@EqualsAndHashCode(exclude = "boxedBreadList")
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
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", referencedColumnName = "status_id")
	private Status status;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	@OneToMany(
	        mappedBy = "myboxes",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true,
	        fetch = FetchType.LAZY
	    )
	@JsonIgnoreProperties("myboxes")
	private Set<BoxedBreads> boxedBreadList;
	
	@Column(name = "checkoutid")
	private String checkoutid;

}
