package com.pa.service;

import com.pa.dto.JwtAuthRespone;
import com.pa.dto.LoginDto;
import com.pa.dto.UserDto;
import com.pa.entity.User;

public interface UserService {
	public User create(UserDto uDto);
	public JwtAuthRespone login(LoginDto loginDto);
}
