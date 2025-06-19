package com.demo.JavaFirst.service;

import com.demo.JavaFirst.Exception.UserNotFound;
import com.demo.JavaFirst.entity.Employees;
import com.demo.JavaFirst.model.EmployeesModel;
import com.demo.JavaFirst.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void register(EmployeesModel employeesModel) {
        List<String> error = new ArrayList<>();

        Employees employees = new Employees();
        employees.setName(employeesModel.getName());
        employees.setEmail(employeesModel.getEmail());
        employees.setPassword(employeesModel.getPassword());
        employees.setSalary(employeesModel.getSalary());
        employeeRepository.save(employees);
    }


    public List<Employees> getSalaryList() {
        return employeeRepository.findEmployeesWithSalaryGreaterThanTenThousand();
    }
}
