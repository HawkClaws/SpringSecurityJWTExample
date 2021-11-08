package com.example.demo.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Employee;
import com.example.demo.security.User;


public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<Employee> {
    
	 public User findByUsername(String username);
}
