package com.pa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pa.dto.JwtAuthRespone;
import com.pa.dto.LoginDto;
import com.pa.dto.UserDto;
import com.pa.entity.User;
import com.pa.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping(path = "/save")
	public ResponseEntity<User> create(@RequestBody UserDto uDto) {
		return new ResponseEntity<User>(service.create(uDto), HttpStatus.CREATED);
	}
	
	//Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthRespone> login(@RequestBody LoginDto loginDto){
        JwtAuthRespone authRespone = service.login(loginDto);      
        return new ResponseEntity<>(authRespone, HttpStatus.OK);
    }
}
