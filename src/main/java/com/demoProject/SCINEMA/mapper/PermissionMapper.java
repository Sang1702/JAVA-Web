package com.demoProject.SCINEMA.mapper;

import com.demoProject.SCINEMA.dto.reponse.PermissionResponse;
import com.demoProject.SCINEMA.dto.request.PermissionRequest;
import com.demoProject.SCINEMA.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper
{
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
