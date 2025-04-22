package com.example.backend.service;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
//    void saveUser(UserDTO userDTO);
//
//    User authenticateUser(String email, String password);

    User updateUser(UserDTO userDTO);


    List<UserDTO> allUser();

    Optional<User> getUserId(int id);



    boolean deleteuser(int id);
}
