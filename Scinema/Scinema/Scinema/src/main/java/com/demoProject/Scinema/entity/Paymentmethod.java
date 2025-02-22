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
public class Paymentmethod {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String paymentMethod_id;
    @Column(nullable = false)
    String method_name;
    String description;
}
