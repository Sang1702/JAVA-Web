package com.demoProject.SCINEMA.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String bill_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    Double total_price;
    LocalDateTime build_date;
    String status;

    @ManyToOne
    @JoinColumn(name = "paymentMethod_id")
    Paymentmethod paymentmethod;
}
