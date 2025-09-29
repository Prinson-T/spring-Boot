package com.demo.JavaFirst.service;

import com.demo.JavaFirst.Exception.Validation;
import com.demo.JavaFirst.entity.Demo;
import com.demo.JavaFirst.entity.Employees;
import com.demo.JavaFirst.model.EmployeesModel;
import com.demo.JavaFirst.model.UserModel;
import com.demo.JavaFirst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void register(UserModel userModel) {

        Demo demo=new Demo();
        demo.setName(userModel.getName());
        demo.setEmail(userModel.getEmail());
        demo.setPassword(userModel.getPassword());
        demo.setNumber(userModel.getNumber());
        demo.setAddress(userModel.getAddress());
        demo.setLocation(userModel.getLocation());
        demo.setUsername(userModel.getUsername());
        userRepository.save(demo);
    }

    public void login(UserModel userModel) {
        List<String> error = new ArrayList<>();
        Demo demo=userRepository.loginByUserNameAndPassword(userModel.getUsername(),userModel.getPassword());
        if (demo == null){
            error.add("invalid username  or Password ");
        }
        if (!error.isEmpty()){
            throw  new Validation(error,"error");
        }
    }
}
