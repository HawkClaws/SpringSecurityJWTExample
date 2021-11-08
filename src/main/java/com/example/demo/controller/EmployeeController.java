package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class EmployeeController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public Employee get() {
    	
    	Employee emp = new Employee();
    	emp.setUsername("tanaka");
    	
        return emp;
    }
    

}
