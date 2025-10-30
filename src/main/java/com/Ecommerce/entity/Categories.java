package com.Ecommerce.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	
	public Categories() {
		
	}
	public Categories(Long id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	public Long getId() {
		return id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
