package com.example.backend.controller;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.service.impl.UserServiceImpl;
import com.example.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api/v1/user")

public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @PutMapping(path = "update")
    public ResponseEntity<ResponseUtil> updateUser(@RequestBody UserDTO userDTO) {
        User user = userService.updateUser(userDTO);

        if (user != null) {
            return ResponseEntity.ok(new ResponseUtil(200, "User updated", user));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseUtil(400, "User update failed. Invalid details provided.", null));
        }
    }


    @GetMapping("getAll")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> user = userService.allUser();
        return user;
    }

    @GetMapping("getUserId/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        Optional<User> user=userService.getUserId(id);
        return user;
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteExam(@PathVariable int id) {
        userService.deleteuser(id);
        return true;

    }


}
