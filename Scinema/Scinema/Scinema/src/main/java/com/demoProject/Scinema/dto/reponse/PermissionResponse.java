package com.demoProject.Scinema.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PermissionResponse
{
    String permission_name;
    String description;
}
