package com.demoProject.SCINEMA.mapper;

import com.demoProject.SCINEMA.dto.reponse.RoleReponse;
import com.demoProject.SCINEMA.dto.request.RoleRequest;
import com.demoProject.SCINEMA.entity.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper
{
    @Mapping(target = "permissions", ignore = true)
    Roles toRole(RoleRequest request);

    RoleReponse toRoleResponse(Roles roles);
}
