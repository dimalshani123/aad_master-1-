package com.example.employeems.repo;


import com.example.employeems.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepo extends JpaRepository<Theater, Integer> {

}