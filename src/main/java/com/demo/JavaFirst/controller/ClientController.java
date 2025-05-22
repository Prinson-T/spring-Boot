package com.demo.JavaFirst.controller;

import com.demo.JavaFirst.Exception.UserNotFound;
import com.demo.JavaFirst.Exception.Validation;
import com.demo.JavaFirst.entity.Employees;
import com.demo.JavaFirst.entity.Sample;
import com.demo.JavaFirst.model.ClientModel;
import com.demo.JavaFirst.response.ResponseHandler;
import com.demo.JavaFirst.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")

public class ClientController {
    @Autowired
    ClientService clientService;

        @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClientModel clientModel){
        clientService.register(clientModel);
       try {
           clientService.register(clientModel);
           return ResponseHandler.generate(null,"User registered successfully",HttpStatus.OK);
       }catch (Validation e){
           return ResponseHandler.generate(e.getError(),"Failed",HttpStatus.BAD_REQUEST);
       }

    }
    @PostMapping ("/login")
    public ResponseEntity<?>login(@RequestBody ClientModel clientModel){
        try {
            clientService.login(clientModel);
            return ResponseHandler.generate(null,"login successful",HttpStatus.OK);
        }catch (Validation e){
            return ResponseHandler.generate(e.getError(),"failed",HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("list/{id}")
    public ResponseEntity<?> update(@RequestBody ClientModel clientModel, @PathVariable("id")Long id){

            try {
                clientService.update(clientModel,id);
                return ResponseHandler.generate(null,"User registered successfully",HttpStatus.OK);
            }catch (Validation e){
                return ResponseHandler.generate(e.getError(),"failed",HttpStatus.BAD_REQUEST);
            }
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Sample>>search(@PathVariable("name")String name){
        List<Sample> samples = clientService.searchAll(name);
            return ResponseEntity.ok(samples);

    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String>deleteById(@PathVariable Long id ){
            clientService.deleteById(id);
            return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
    }


    @GetMapping("/list")
    public ResponseEntity<List<Sample>> getList() {
        List<Sample> sampleList = clientService.getList();
        return ResponseEntity.ok(sampleList);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Sample user = clientService.find(id);
        return ResponseEntity.ok(user);
    }
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String>handle(UserNotFound userNotFound){
        return new ResponseEntity<>(userNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {

        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
