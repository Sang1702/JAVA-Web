package com.demoProject.Scinema.service;


import com.demoProject.Scinema.dto.reponse.PermissionResponse;
import com.demoProject.Scinema.dto.request.PermissionRequest;
import com.demoProject.Scinema.entity.Permission;
import com.demoProject.Scinema.mapper.PermissionMapper;
import com.demoProject.Scinema.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class PermissionService
{
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request)
    {
        Permission permission = permissionMapper.toPermission(request);

        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll()
    {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission)
    {
        permissionRepository.deleteById(permission);
    }
}
