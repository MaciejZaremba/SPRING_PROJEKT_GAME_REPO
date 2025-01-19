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
    @Column(unique=true, name="apiid")
    private Long apiId;
    private String name;
    @ManyToMany
    private List<Genre> genres;
    @ManyToMany
    private List<Theme> themes;
    private Integer releaseYear;
    private Double rating;
    @ManyToMany
    private List<Platform> platforms;
    @ManyToMany
    private List<Company> companies;

    public Game(Long apiId, String name, List<Genre> genres, List<Theme> themes, Integer releaseYear, Double rating, List<Platform> platforms, List<Company> companies) {
        this.apiId = apiId;
        this.name = name;
        this.genres = genres;
        this.themes = themes;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.platforms = platforms;
        this.companies = companies;
    }

}
