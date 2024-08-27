package com.pa.mapper;

import com.pa.dto.TodoDto;
import com.pa.entity.Todo;

public class TodoMapper {
	public static TodoDto mapTotodoDto(Todo todo) {
		return new TodoDto(
					todo.getId(),
					todo.getTitle(),
					todo.getDescription(),
					todo.getComplete()
				);
	}
	
	public static Todo mapTotodo(TodoDto todoDto) {
		return new Todo(
				todoDto.getId(),
				todoDto.getTitle(),
				todoDto.getDescription(),
				todoDto.getComplete()
				);
	}
}
