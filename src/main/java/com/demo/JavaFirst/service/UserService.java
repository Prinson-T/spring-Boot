package com.demo.JavaFirst.service;

import com.demo.JavaFirst.entity.Demo;
import com.demo.JavaFirst.model.UserModel;
import com.demo.JavaFirst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void register(UserModel userModel) {

        Demo demo=new Demo();
        demo.setName(userModel.getName());
        demo.setEmail(userModel.getEmail());
        demo.setPassword(userModel.getPassword());
        userRepository.save(demo);
    }
}
