package com.demo.JavaFirst.model;

public class UserModel {
    private String name;
    private String email;
    private String password;
    private String number;
    private String address;
    private String location;
    private String username;

    public UserModel() {

    }

    public UserModel(String name, String email, String password, String number, String address, String location, String username) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.number = number;
        this.address = address;
        this.location = location;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
