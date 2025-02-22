package com.demoProject.Scinema.service;


import com.demoProject.Scinema.dto.reponse.RoleReponse;
import com.demoProject.Scinema.dto.request.RoleRequest;
import com.demoProject.Scinema.mapper.RoleMapper;
import com.demoProject.Scinema.repository.PermissionRepository;
import com.demoProject.Scinema.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RoleService
{
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleReponse create(RoleRequest request)
    {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleReponse> getAll()
    {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String role)
    {
        roleRepository.deleteById(role);
    }
}
