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
public class Paymentmethods {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String payment_method_id;
    @Column(nullable = false)
    String method_name;
    String description;
}
