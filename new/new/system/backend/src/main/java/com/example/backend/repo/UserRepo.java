package com.example.backend.repo;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email); // Check if a user with the given email exists
    boolean existsByNic(String nic);

    Optional<User> findByUserId(int mobile);


    void deleteByUserId(int mobile);
    Optional<User> findByEmail(String email);

}
