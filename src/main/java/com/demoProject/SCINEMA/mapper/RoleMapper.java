package com.demoProject.SCINEMA.mapper;

import com.demoProject.SCINEMA.dto.reponse.RoleReponse;
import com.demoProject.SCINEMA.dto.request.RoleRequest;
import com.demoProject.SCINEMA.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper
{
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleReponse toRoleResponse(Role role);
}
