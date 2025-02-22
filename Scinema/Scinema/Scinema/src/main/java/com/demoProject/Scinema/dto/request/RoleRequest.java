package com.demoProject.Scinema.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleRequest
{
    String role_name;
    String description;

    Set<String> permissions;
}
