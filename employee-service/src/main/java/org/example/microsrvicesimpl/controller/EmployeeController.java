package org.example.microsrvicesimpl.controller;

import org.example.microsrvicesimpl.entity.Employee;
import org.example.microsrvicesimpl.response.EmployeeResponse;
import org.example.microsrvicesimpl.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @GetMapping("employees/{id}")
    ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
        // call databases --> employee id:1
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse );
    }
}
