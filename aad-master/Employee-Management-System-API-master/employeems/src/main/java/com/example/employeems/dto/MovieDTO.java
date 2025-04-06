package com.example.employeems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class MovieDTO {
    private int mvId;

    public MovieDTO(int mvId, String mvName, String language, String genre, String time) {
        this.mvId = mvId;
        this.mvName = mvName;
        this.language = language;
        this.genre = genre;
        this.time = time;
    }

    public int getMvId() {
        return mvId;
    }

    public void setMvId(int mvId) {
        this.mvId = mvId;
    }

    public String getMvName() {
        return mvName;
    }

    public MovieDTO() {
    }

    public void setMvName(String mvName) {
        this.mvName = mvName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String mvName;
    private String language;
    private String genre;
    private String time;
}
