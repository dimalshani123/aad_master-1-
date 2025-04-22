package com.example.backend.service;
import com.example.backend.dto.AuthRequestDTO;
import com.example.backend.dto.RegisterRequestDTO;
import com.example.backend.dto.ResponseDTO;

import com.example.backend.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseDTO> userRegister(RegisterRequestDTO registerRequestDTO);
    ResponseEntity<ResponseDTO> userLogin(AuthRequestDTO authRequestDTO);


}