package com.demoProject.SCINEMA.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserUpdateRequest
{
    String password;
    String fullname;
    String email;
    LocalDate dob;

    List<String> roles;
}
