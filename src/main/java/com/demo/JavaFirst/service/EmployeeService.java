package com.demo.JavaFirst.service;

import com.demo.JavaFirst.Exception.UserNotFound;
import com.demo.JavaFirst.Exception.Validation;
import com.demo.JavaFirst.entity.Employees;
import com.demo.JavaFirst.entity.Sample;
import com.demo.JavaFirst.model.ClientModel;
import com.demo.JavaFirst.model.EmployeesModel;
import com.demo.JavaFirst.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)){
            throw new UserNotFound("User not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public EmployeesModel register(EmployeesModel employeesModel) {
        List<String> error = new ArrayList<>();

        Employees employees = new Employees();
        employees.setName(employeesModel.getName());
        employees.setEmail(employeesModel.getEmail());
        employees.setPassword(employeesModel.getPassword());
        employees.setSalary(employeesModel.getSalary());
        employeeRepository.save(employees);
        return employeesModel;
    }

    public void login(EmployeesModel employeesModel) {
        List<String> error = new ArrayList<>();
        Employees employees=employeeRepository.loginByEmailAndPassword(employeesModel.getEmail(),employeesModel.getPassword());
        if (employees == null){
            error.add("invalid mail Id or Password ");
        }
        if (!error.isEmpty()){
            throw  new Validation(error,"error");
        }
    }


    public List<Employees> getSalaryList() {
        return employeeRepository.findEmployeesWithSalaryGreaterThanTenThousand();
    }

    public List<Sample> searchAll(String name) {
        return employeeRepository.findByAllData(name);

    }

    public List<Employees> getList() {
        return employeeRepository.findAll();
    }


    public Employees update(EmployeesModel employeesModel, Long id) {
        List<String> error = new ArrayList<>();
        if (employeesModel.getName() ==  null || employeesModel.getName().isEmpty()){
            error.add("error");
        }
        int count = employeeRepository.countById(id);
        if (count == 0){
            error.add("not found");

        }
        if (!error.isEmpty()){
            throw  new Validation(error,"error");
        }

        Employees sample = employeeRepository.findById(id).orElseThrow(() -> new UserNotFound("clint not found "+id));
        sample.setName(employeesModel.getName());
        sample.setPassword(employeesModel.getPassword());
        sample.setEmail(employeesModel.getEmail());
        return employeeRepository.save(sample);
    }

    public Employees find(Long id) {
        return employeeRepository.findById(id).orElseThrow(()-> new UserNotFound("client not found "+ id));

    }



}
