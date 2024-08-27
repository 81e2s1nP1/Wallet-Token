package com.pa.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

import com.pa.config.JwtTokenProvider;
import com.pa.dto.JwtAuthRespone;
import com.pa.dto.LoginDto;
import com.pa.dto.UserDto;
import com.pa.entity.Role;
import com.pa.entity.User;
import com.pa.exception.TodoAPIException;
import com.pa.repository.UserRepository;
import com.pa.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserRepository repository;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager ;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public User create(UserDto uDto) {
		if(repository.findByUsernameOrEmail(uDto.getUsername(), uDto.getEmail()) != null) {
			throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email or Username exist");
		}else {			
			Set<Role> roles = new HashSet<>();
			Role role = new Role();
			role.setName("ROLE_ADMIN");
			roles.add(role);
			User u = new User(uDto.getUsername(),
					uDto.getEmail(),
					passwordEncoder.encode(uDto.getPassword()),
					roles);
			return repository.save(u);
		}
	}

	@Override
	public JwtAuthRespone login(LoginDto loginDto) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String token = jwtTokenProvider.generateToken(authentication);
        User user = repository.findByEmail(loginDto.getEmail());
        
        String 	str_role = null;
        if(user != null) {
        	Optional<Role> role = user.getRole().stream().findFirst();
        	if(role.isPresent()) {
        		Role userRole = role.get();
        		str_role = userRole.getName();
        	}
        }
        
        JwtAuthRespone jwtAuthRespone = new JwtAuthRespone();
        jwtAuthRespone.setRole(str_role);
        jwtAuthRespone.setAccessToken(token);
        return jwtAuthRespone;
	}

}
