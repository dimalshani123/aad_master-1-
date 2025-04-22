package com.example.backend.dto;

import com.example.backend.entity.UserType;

public class RegisterRequestDTO {

    private String name;
    private String nic;
    private String email;
    private String password;
    private UserType userType;


    public RegisterRequestDTO(String name, String nic, String email, String password, UserType userType) {
        this.name = name;
        this.nic = nic;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}