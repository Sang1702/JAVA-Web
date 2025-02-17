package com.demoProject.SCINEMA.dto.request;

import com.demoProject.SCINEMA.entity.Permission;
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
    String name;
    String description;
    Set<String> permissions;
}
