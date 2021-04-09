package com.agatha.tasllist.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 255)
	private String title;
	
	@Column(nullable = false, length = 255)
	private String description;
	
	@Column(nullable = false)
	private Boolean status;
	
	@Column(nullable = false)
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date created_at;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_at;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date remove_at;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date concluded;
	
	@Column(nullable = false, unique = true)
	private Integer position;
	
}
