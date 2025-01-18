package com.pjait.Games_Data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer apiID;
    private String name;
    @ManyToMany
    private List<Genre> genres;
    @ManyToMany
    private List<Theme> themes;
    private Integer releaseYear;
    private Double rating;
    @ManyToMany
    private List<Platform> platforms;
    @ManyToOne
    private Company company;

    public Game(Integer apiID, String name, List<Genre> genres, List<Theme> themes, Integer releaseYear, Double rating, List<Platform> platforms, Company company) {
        this.apiID = apiID;
        this.name = name;
        this.genres = genres;
        this.themes = themes;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.platforms = platforms;
        this.company = company;
    }

}
