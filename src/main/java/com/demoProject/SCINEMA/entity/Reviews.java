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
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String review_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movies movie;

    Integer rating;
    @Column(length = 1000)
    String review_text;
}
