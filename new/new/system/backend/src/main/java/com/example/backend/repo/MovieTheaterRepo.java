// MovieTheaterRepo.java
package com.example.backend.repo;


import com.example.backend.entity.MovieTheater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieTheaterRepo extends JpaRepository<MovieTheater, Integer> {
    
    @Query("SELECT mt FROM MovieTheater mt WHERE mt.movie.mvId = :movieId")
    List<MovieTheater> findByMovieId(@Param("movieId") int movieId);
    
    @Query("SELECT mt FROM MovieTheater mt WHERE mt.theater.id = :theaterId")
    List<MovieTheater> findByTheaterId(@Param("theaterId") int theaterId);
    
    boolean existsByMovieMvIdAndTheaterId(int movieId, int theaterId);
}