package com.demoProject.Scinema.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String genre_id;
    String name_genre;
}
