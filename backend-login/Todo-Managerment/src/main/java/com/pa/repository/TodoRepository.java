package com.pa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pa.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
