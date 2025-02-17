package com.demoProject.SCINEMA.service;

import com.demoProject.SCINEMA.dto.reponse.PermissionResponse;
import com.demoProject.SCINEMA.dto.request.PermissionRequest;
import com.demoProject.SCINEMA.entity.Permission;
import com.demoProject.SCINEMA.mapper.PermissionMapper;
import com.demoProject.SCINEMA.repository.PermissionRepository;
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
