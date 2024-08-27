	package com.pa.dto;

public class TodoDto {
	private Long id; 
	private String title;
	private String description;
	private Boolean complete;
	
	public TodoDto() {
	}
	
	public TodoDto(String title, String description, Boolean complete) {
		this.title = title;
		this.description = description;
		this.complete = complete;
	}
	public TodoDto(Long id, String title, String description, Boolean complete) {
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
