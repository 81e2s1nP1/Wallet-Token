package com.pa.service;

import java.util.List;

import com.pa.dto.TodoDto;

public interface TodoService {
	public TodoDto create(TodoDto todoDto);
	public List<TodoDto> getall();
	public TodoDto getById(Long id);
	public TodoDto  update(Long id, TodoDto todoDto);
	public Boolean delete(Long id);
	public TodoDto completeTodo(Long id);
	public TodoDto inCompleteTodo(Long id);
}
