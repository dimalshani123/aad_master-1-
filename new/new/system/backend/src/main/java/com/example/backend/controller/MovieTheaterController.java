// MovieTheaterController.java
package com.example.backend.controller;


import com.example.backend.dto.MovieTheaterDTO;
import com.example.backend.dto.MovieTheaterResponseDTO;
import com.example.backend.service.impl.MovieTheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/movie-theaters")
public class MovieTheaterController {

    @Autowired
    private MovieTheaterService movieTheaterService;

//    @PostMapping
//    public ResponseEntity<MovieTheaterResponseDTO> addMovieTheater(@RequestBody MovieTheaterDTO movieTheaterDTO) {
//        return ResponseEntity.ok(movieTheaterService.addMovieTheater(movieTheaterDTO));
//    }

    @PostMapping
    public ResponseEntity<MovieTheaterResponseDTO> addMovieTheater(@RequestBody MovieTheaterDTO movieTheaterDTO) {
        try {
            return ResponseEntity.ok(movieTheaterService.addMovieTheater(movieTheaterDTO));
        } catch (Exception e) {
            e.printStackTrace();  // Log the error for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<MovieTheaterResponseDTO>> getAllMovieTheaters() {
        return ResponseEntity.ok(movieTheaterService.getAllMovieTheaters());
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<MovieTheaterResponseDTO>> getByMovieId(@PathVariable int movieId) {
        return ResponseEntity.ok(movieTheaterService.getByMovieId(movieId));
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<MovieTheaterResponseDTO>> getByTheaterId(@PathVariable int theaterId) {
        return ResponseEntity.ok(movieTheaterService.getByTheaterId(theaterId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieTheater(@PathVariable int id) {
        movieTheaterService.deleteMovieTheater(id);
        return ResponseEntity.noContent().build();
    }


}