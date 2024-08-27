package com.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pa.entity.Role;

public interface RoleRepostitory extends JpaRepository<Role, Long> {

}
