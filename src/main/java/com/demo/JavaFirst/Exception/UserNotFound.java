package com.demo.JavaFirst.Exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound (String message){
        super(message);
    }

}
