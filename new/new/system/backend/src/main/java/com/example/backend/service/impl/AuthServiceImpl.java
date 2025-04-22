package com.example.backend.service.impl;

import com.example.backend.dto.AuthRequestDTO;
import com.example.backend.dto.RegisterRequestDTO;
import com.example.backend.dto.ResponseDTO;
import com.example.backend.entity.User;
import com.example.backend.exception.ResourceAlreadyExistException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.repo.UserRepo;
import com.example.backend.service.AuthService;
import com.example.backend.util.JwtUtil;
import com.example.backend.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service

public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ResponseDTO> userRegister(RegisterRequestDTO registerRequestDTO) {

        Optional<User> optionUser = userRepository.findByEmail(registerRequestDTO.getEmail());
        if(optionUser.isPresent()) {
            throw new ResourceAlreadyExistException("Email already exists");
        }

//        if(userRepository.findByEmail(registerRequestDTO.getEmail()) != null){
//            throw new ResourceAlreadyExistException("Email already exists");
//        }

        try{
            Map<String,Object> resToken = new HashMap<>();
            registerRequestDTO.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

            User user = userRepository.save(modelMapper.map(registerRequestDTO, User.class));
            String token = jwtUtil.generateToken(registerRequestDTO.getEmail(), registerRequestDTO.getUserType().toString());
            resToken.put("token", token);
            resToken.put("role", user.getUserType().toString());

            resToken.put("email", user.getEmail());
            resToken.put("id", user.getUserId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"User register successfully",resToken));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public ResponseEntity<ResponseDTO> userLogin(AuthRequestDTO authRequestDTO) {
       Optional<User> user = userRepository.findByEmail(authRequestDTO.getEmail());
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User not found");

        }
       User user1= user.get();
        if (!passwordEncoder.matches(authRequestDTO.getPassword(), user1.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }


        try{
            String token = jwtUtil.generateToken(authRequestDTO.getEmail(), user1.getUserType().toString());
            Map<String,Object> resToken = new HashMap<>();
            resToken.put("token", token);
            resToken.put("role", user1.getUserType().toString());

            resToken.put("email", user1.getEmail());
            resToken.put("id", user1.getUserId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDTO(VarList.Created,"User login successfully",resToken));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
