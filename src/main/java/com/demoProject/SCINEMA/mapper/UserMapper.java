package com.demoProject.SCINEMA.mapper;

import com.demoProject.SCINEMA.dto.reponse.UserResponse;
import com.demoProject.SCINEMA.dto.request.UserCreationRequest;
import com.demoProject.SCINEMA.dto.request.UserUpdateRequest;
import com.demoProject.SCINEMA.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    Users toUser(UserCreationRequest request);
    UserResponse toUserResponse(Users user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget Users user, UserUpdateRequest request);
}
