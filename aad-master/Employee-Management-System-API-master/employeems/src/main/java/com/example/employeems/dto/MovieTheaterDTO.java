// MovieTheaterDTO.java
package com.example.employeems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class MovieTheaterDTO {
    private int movieId;

    public MovieTheaterDTO(int movieId, int theaterId) {
        this.movieId = movieId;
        this.theaterId = theaterId;
    }

    private int theaterId;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public MovieTheaterDTO() {
    }
}