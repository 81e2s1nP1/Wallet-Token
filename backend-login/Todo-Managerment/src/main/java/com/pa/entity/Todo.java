package com.pa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Entity
@Table(name = "todos")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	private Boolean complete;
	
	public Todo() {
	}
	
	public Todo(String title, String description, Boolean complete) {
		this.title = title;
		this.description = description;
		this.complete = complete;
	}
	public Todo(Long id, String title, String description, Boolean complete) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.complete = complete;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	
	
}
