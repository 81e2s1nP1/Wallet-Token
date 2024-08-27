package com.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User  findByEmail(String email);
	User  findByUsernameOrEmail(String username, String email);
}
