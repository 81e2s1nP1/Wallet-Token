package com.pa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pa.dto.TodoDto;
import com.pa.service.TodoService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/todo")
public class TodoController {
	@Autowired
	private TodoService service;

	public TodoController(TodoService service) {
		this.service = service;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(path = "/create")
	public ResponseEntity<TodoDto> create(@RequestBody TodoDto todoDto){
		TodoDto Tdto = service.create(todoDto);
		return new ResponseEntity<TodoDto>(Tdto, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(path = "/getall")
	public ResponseEntity<List<TodoDto>> getAll() {
	    List<TodoDto> todos = service.getall();
	    return new ResponseEntity<List<TodoDto>>(todos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/getbyid")
	public ResponseEntity<TodoDto> getById(@RequestParam("id") Long id) {
		TodoDto todoDto = service.getById(id);
		return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
	}
	
	@PutMapping(path = "/update")
	public ResponseEntity<TodoDto> update(@RequestParam("id") Long id, @RequestBody TodoDto todoDto) {
		TodoDto Tdto = service.update(id, todoDto);
		return new ResponseEntity<TodoDto>(Tdto, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete")
	public ResponseEntity<Boolean> delete(@RequestParam("id") Long id) {
		boolean isDelete = service.delete(id);
		return new ResponseEntity<Boolean>(isDelete, HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PatchMapping(path = "{id}/complete")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id) {
		TodoDto updateTodo = service.completeTodo(id);
		return new ResponseEntity<TodoDto>(updateTodo, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@PatchMapping(path = "{id}/incomplete")
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id) {
		TodoDto updateTodo = service.inCompleteTodo(id);
		return new ResponseEntity<TodoDto>(updateTodo, HttpStatus.OK);
	}
}
