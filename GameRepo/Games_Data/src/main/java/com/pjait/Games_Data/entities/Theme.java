package com.pjait.Games_Data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private Long apiId;
    private String name;

    public Theme(Long id, Long apiId, String name) {
        this.id = id;
        this.apiId = apiId;
        this.name = name;
    }
}
