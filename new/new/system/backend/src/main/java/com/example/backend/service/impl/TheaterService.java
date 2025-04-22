package com.example.backend.service.impl;


import com.example.backend.dto.TheaterDTO;
import com.example.backend.entity.Theater;
import com.example.backend.repo.TheaterRepo;
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
public class TheaterService {

    @Autowired
    private TheaterRepo theaterRepo;

    @Autowired
    private ModelMapper modelMapper;


    public String saveTheater(TheaterDTO theaterDTO){
        if (theaterRepo.existsById(theaterDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            theaterRepo.save(modelMapper.map(theaterDTO, Theater.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateTheater(TheaterDTO theaterDTO){
        if (theaterRepo.existsById(theaterDTO.getId())){
            theaterRepo.save(modelMapper.map(theaterDTO, Theater.class));
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<TheaterDTO> getAllTheater(){
        List<Theater> theaterList = theaterRepo.findAll();
        return modelMapper.map(theaterList,new TypeToken<ArrayList<TheaterDTO>>(){
        }.getType());
    }

    public TheaterDTO searchTheater(int Id){
        if (theaterRepo.existsById(Id)){
            Theater theater =theaterRepo.findById(Id).orElse(null);
            return modelMapper.map(theater,TheaterDTO.class);
        }else {
            return null;
        }
    }
    public String deleteTheater(int Id){
        if (theaterRepo.existsById(Id)){
            theaterRepo.deleteById(Id);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
