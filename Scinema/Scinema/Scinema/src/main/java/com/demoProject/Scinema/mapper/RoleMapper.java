package com.demoProject.Scinema.mapper;


import com.demoProject.Scinema.dto.reponse.RoleReponse;
import com.demoProject.Scinema.dto.request.RoleRequest;
import com.demoProject.Scinema.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper
{
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleReponse toRoleResponse(Role role);
}
