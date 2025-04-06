// MovieTheaterService.java
package com.example.employeems.service;

import com.example.employeems.dto.MovieTheaterDTO;
import com.example.employeems.dto.MovieTheaterResponseDTO;
import com.example.employeems.entity.Movie;
import com.example.employeems.entity.MovieTheater;
import com.example.employeems.entity.Theater;
import com.example.employeems.repo.MovieRepo;
import com.example.employeems.repo.MovieTheaterRepo;
import com.example.employeems.repo.TheaterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieTheaterService {

    @Autowired
    private MovieTheaterRepo movieTheaterRepo;
    
    @Autowired
    private MovieRepo movieRepo;
    
    @Autowired
    private TheaterRepo theaterRepo;

    public MovieTheaterResponseDTO addMovieTheater(MovieTheaterDTO movieTheaterDTO) {
        if (movieTheaterRepo.existsByMovieMvIdAndTheaterId(movieTheaterDTO.getMovieId(), movieTheaterDTO.getTheaterId())) {
            throw new RuntimeException("This movie is already assigned to this theater");
        }
        
        Movie movie = movieRepo.findById(movieTheaterDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
                
        Theater theater = theaterRepo.findById(movieTheaterDTO.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found"));
                
        MovieTheater movieTheater = new MovieTheater();
        movieTheater.setMovie(movie);
        movieTheater.setTheater(theater);
        
        MovieTheater saved = movieTheaterRepo.save(movieTheater);
        return convertToResponseDTO(saved);
    }

    public List<MovieTheaterResponseDTO> getAllMovieTheaters() {
        return movieTheaterRepo.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MovieTheaterResponseDTO> getByMovieId(int movieId) {
        return movieTheaterRepo.findByMovieId(movieId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MovieTheaterResponseDTO> getByTheaterId(int theaterId) {
        return movieTheaterRepo.findByTheaterId(theaterId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteMovieTheater(int id) {
        movieTheaterRepo.deleteById(id);
    }

    private MovieTheaterResponseDTO convertToResponseDTO(MovieTheater movieTheater) {
        return new MovieTheaterResponseDTO(
                movieTheater.getId(),
                movieTheater.getMovie().getMvId(),
                movieTheater.getMovie().getMvName(),
                movieTheater.getTheater().getId(),
                movieTheater.getTheater().getName()
        );
    }
}