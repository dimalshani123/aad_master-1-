package com.example.backend.service.impl;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.repo.UserRepo;
import com.example.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public void saveUser(UserDTO userDTO) {
//        if (userRepo.existsById(userDTO.getUserId())){
//            throw new RuntimeException("user already exists");
//        }
//        User user = modelMapper.map(userDTO, User.class);
//        userRepo.save(user);
//
//    }

//    @Override
//    public void saveUser(UserDTO userDTO) {
//        // Check if a user with the same email already exists
//        if (userRepo.existsByEmail(userDTO.getEmail())) {
//            throw new RuntimeException("User with this email already exists: " + userDTO.getEmail());
//        }
//
//        // Check if a user with the same NIC already exists
//        if (userRepo.existsByNic(userDTO.getNic())) {
//            throw new RuntimeException("User with this NIC already exists: " + userDTO.getNic());
//        }
//
//        // Map UserDTO to User entity
//        User user = modelMapper.map(userDTO, User.class);
//
//        // Save the user
//        userRepo.save(user);
//    }

    public User authenticateUser(String email, String password) {
        User user = userRepo.findByEmailAndPassword(email, password);

        if (user == null) {
            throw new RuntimeException("User not found! Please register first.");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password! Please try again.");
        }

        return user;
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        return userRepo.findById(userDTO.getUserId())
                .map(existingUser -> {
                    User updatedUser = modelMapper.map(userDTO, User.class);
                    return userRepo.save(updatedUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found! Please try again."));

    }

    @Override
    public List<UserDTO> allUser() {
        return modelMapper.map(userRepo.findAll(),
                new TypeToken<List<UserDTO>>() {}.getType());
    }

    @Override
    public Optional<User> getUserId(int id) {
        return userRepo.findById(id);
    }

    @Override
    public boolean deleteuser(int id) {
        userRepo.deleteById(id);
        return true;

    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user1=user.get();


        return new org.springframework.security.core.userdetails.User(
                user1.getEmail(), user1.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user1.getUserType().toString())));
    }





}