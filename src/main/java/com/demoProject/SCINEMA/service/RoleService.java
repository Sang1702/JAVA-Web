package com.demoProject.SCINEMA.service;

import com.demoProject.SCINEMA.dto.reponse.RoleReponse;
import com.demoProject.SCINEMA.dto.request.RoleRequest;
import com.demoProject.SCINEMA.mapper.RoleMapper;
import com.demoProject.SCINEMA.repository.PermissionRepository;
import com.demoProject.SCINEMA.repository.RoleRepository;
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
