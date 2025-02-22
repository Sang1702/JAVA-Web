package com.demoProject.Scinema.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleReponse
{
    String role_name;
    String description;

    Set<PermissionResponse> permissions;
}
