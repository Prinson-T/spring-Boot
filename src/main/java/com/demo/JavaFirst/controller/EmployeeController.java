package com.demo.JavaFirst.controller;

import com.demo.JavaFirst.Exception.Validation;
import com.demo.JavaFirst.entity.Employees;
import com.demo.JavaFirst.model.EmployeesModel;
import com.demo.JavaFirst.response.ResponseHandler;
import com.demo.JavaFirst.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody EmployeesModel employeesModel) {
        employeeService.register(employeesModel);
        return ResponseHandler.generate(null, "User registered successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteById(@PathVariable Long id){
        employeeService.deleteById(id);
        return ResponseEntity.ok("User with ID " +id+" deleted successfully.");

    }

@GetMapping("/salary")
    public ResponseEntity<List<Employees>>salary(){
        List<Employees>employees= employeeService.getSalaryList();
        return ResponseEntity.ok(employees);
}
}
