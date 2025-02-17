package com.demoProject.SCINEMA.mapper;

import com.demoProject.SCINEMA.dto.reponse.PermissionReponse;
import com.demoProject.SCINEMA.dto.request.PermissiongRequest;
import com.demoProject.SCINEMA.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper
{
    Permission toPermission(PermissiongRequest request);
    PermissionReponse toPermissionResponse(Permission permission);
}
