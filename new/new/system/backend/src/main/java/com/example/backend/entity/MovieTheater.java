package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "movie_theater")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieTheater {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "mv_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "id", nullable = false)
    private Theater theater;

}
