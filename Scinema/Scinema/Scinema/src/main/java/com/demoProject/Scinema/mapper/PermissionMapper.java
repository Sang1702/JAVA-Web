package com.demoProject.Scinema.mapper;

import com.demoProject.Scinema.dto.reponse.PermissionResponse;
import com.demoProject.Scinema.dto.request.PermissionRequest;
import com.demoProject.Scinema.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper
{
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
