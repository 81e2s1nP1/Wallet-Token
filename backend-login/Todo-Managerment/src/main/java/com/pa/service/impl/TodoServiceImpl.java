package com.pa.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa.dto.TodoDto;
import com.pa.entity.Todo;
import com.pa.exception.ResourceNotfoundException;
import com.pa.mapper.TodoMapper;
import com.pa.repository.TodoRepository;
import com.pa.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{
	@Autowired
	private TodoRepository todoRepository;
	
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public TodoDto create(TodoDto todoDto) {
		Todo todo = TodoMapper.mapTotodo(todoDto);
		return TodoMapper.mapTotodoDto(todoRepository.save(todo));
	}

	@Override
	public List<TodoDto> getall() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream().map((todo) -> TodoMapper.mapTotodoDto(todo))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDto getById(Long id) {
		Todo todo = todoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotfoundException("Not found object with id =  " + id));
		return TodoMapper.mapTotodoDto(todo);
	}

	@Override
	public TodoDto update(Long id, TodoDto todoDto) {
		Todo todo =  TodoMapper.mapTotodo(getById(id));
		if(todo != null) {
			todo.setTitle(todoDto.getTitle());
			todo.setDescription(todoDto.getDescription());
			todo.setComplete(todoDto.getComplete());
			
			return TodoMapper.mapTotodoDto(todoRepository.save(todo));
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		boolean isDelete = false;
		Todo todo = TodoMapper.mapTotodo(getById(id));
		if(todo != null) {
			todoRepository.delete(todo);
			isDelete = true;
		}
		return isDelete;
	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotfoundException("Not found object with id =  " + id));
		
		todo.setComplete(Boolean.TRUE);
		Todo updateTodo = todoRepository.save(todo);
		return TodoMapper.mapTotodoDto(updateTodo);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotfoundException("Not found object with id =  " + id));
		todo.setComplete(Boolean.FALSE);
		Todo updateTodo = todoRepository.save(todo);
		return TodoMapper.mapTotodoDto(updateTodo);
	}

}
