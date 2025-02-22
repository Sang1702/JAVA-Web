package com.demoProject.Scinema.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCreationRequest
{
    @NotBlank(message = "Username cannot be empty")
            @Size(min = 3, message = "USERNAME_INVALID")
    String username;
    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).+$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character.")
    String password;
    String fullname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    String email;

    @Past(message = "Date of birth must be in the past")
    LocalDate dob;
}
