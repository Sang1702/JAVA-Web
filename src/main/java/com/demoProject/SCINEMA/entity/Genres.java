package com.demoProject.SCINEMA.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String genre_id;
    String name_genre;
}
