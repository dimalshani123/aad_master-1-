package com.example.backend.service.impl;


import com.example.backend.dto.MovieDTO;
import com.example.backend.entity.Movie;
import com.example.backend.repo.MovieRepo;
import com.example.backend.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ModelMapper modelMapper;


    public String saveMovie(MovieDTO movieDTO){
            movieRepo.save(modelMapper.map(movieDTO, Movie.class));
            return VarList.RSP_SUCCESS;

    }
    public String updateMovie(MovieDTO movieDTO){
        if (movieRepo.existsById(movieDTO.getMvId())){
            movieRepo.save(modelMapper.map(movieDTO, Movie.class));
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<MovieDTO> getAllMovie(){
        List<Movie> movieList = movieRepo.findAll();
        return modelMapper.map(movieList,new TypeToken<ArrayList<MovieDTO>>(){
        }.getType());
    }

    public MovieDTO searchMovie(int mvId){
        if (movieRepo.existsById(mvId)){
            Movie movie =movieRepo.findById(mvId).orElse(null);
            return modelMapper.map(movie,MovieDTO.class);
        }else {
            return null;
        }
    }
    public String deleteMovie(int mvId){
        if (movieRepo.existsById(mvId)){
            movieRepo.deleteById(mvId);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
