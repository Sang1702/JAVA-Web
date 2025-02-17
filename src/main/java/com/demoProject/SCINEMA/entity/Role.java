package com.demoProject.SCINEMA.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role
{
    @Id
    String role_name;
    String description;

    @ManyToMany
    Set<Permission> permissions;
}
