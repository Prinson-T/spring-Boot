package com.demo.JavaFirst.controller;

import com.demo.JavaFirst.Exception.Validation;
import com.demo.JavaFirst.entity.Employees;
import com.demo.JavaFirst.entity.Sample;
import com.demo.JavaFirst.model.ClientModel;
import com.demo.JavaFirst.model.EmployeesModel;
import com.demo.JavaFirst.response.ResponseHandler;
import com.demo.JavaFirst.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.swing.text.Document;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody EmployeesModel employeesModel) {
//        employeeService.register(employeesModel);
//        return ResponseHandler.generate(null, "User registered successfully", HttpStatus.OK);
//
//    }
@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody EmployeesModel model) {
    EmployeesModel saved = employeeService.register(model);
    return ResponseHandler.generate(saved, "User registered successfully", HttpStatus.OK);
}


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteById(@PathVariable Long id){
        employeeService.deleteById(id);
        return ResponseEntity.ok("User with ID " +id+" deleted successfully.");

    }


    @GetMapping("/list")
    public ResponseEntity<List<Employees>> getList() {
        List<Employees> sampleList = employeeService.getList();
        return ResponseEntity.ok(sampleList);
    }

    @PutMapping("list/{id}")
    public ResponseEntity<?> update(@RequestBody EmployeesModel employeesModel, @PathVariable("id")Long id){

        try {
            employeeService.update(employeesModel,id);
            return ResponseHandler.generate(null,"User registered successfully",HttpStatus.OK);
        }catch (Validation e){
            return ResponseHandler.generate(e.getError(),"failed",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Employees user = employeeService.find(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping ("/login")
    public ResponseEntity<?>login(@RequestBody EmployeesModel employeesModel){
        try {
            employeeService.login(employeesModel);
            return ResponseHandler.generate(null,"login successful",HttpStatus.OK);
        }catch (Validation e){
            return ResponseHandler.generate(e.getError(),"failed",HttpStatus.BAD_REQUEST);
        }
uhuyhiuh
    }
}
