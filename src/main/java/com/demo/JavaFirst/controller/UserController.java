package com.demo.JavaFirst.controller;

import com.demo.JavaFirst.Exception.Validation;
import com.demo.JavaFirst.model.UserModel;
import com.demo.JavaFirst.response.ResponseHandler;
import com.demo.JavaFirst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel userModel){
        userService.register(userModel);
        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping ("/login")
    public ResponseEntity<?>login(@RequestBody UserModel userModel){
        try {
            userService.login(userModel);
            return ResponseHandler.generate(null,"login successful",HttpStatus.OK);
        }catch (Validation e){
            return ResponseHandler.generate(e.getError(),"failed",HttpStatus.BAD_REQUEST);
        }
    }
}
