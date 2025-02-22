package com.demoProject.Scinema.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AuthenticationResponse
{
    String token;
    boolean isAuthenticated;
}
