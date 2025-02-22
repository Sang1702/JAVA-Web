package com.demoProject.Scinema.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse
{
    String user_id;
    String username;
    String fullname;
    String email;
    LocalDate dob;

    Set<RoleReponse> roles;
}
