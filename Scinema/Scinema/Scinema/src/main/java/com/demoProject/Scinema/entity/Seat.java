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
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String seat_id;
    String seat_number;
    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;
}
