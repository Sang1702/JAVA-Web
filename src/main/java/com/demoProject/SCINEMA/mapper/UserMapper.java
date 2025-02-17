package com.demoProject.SCINEMA.mapper;

import com.demoProject.SCINEMA.dto.reponse.UserResponse;
import com.demoProject.SCINEMA.dto.request.UserCreationRequest;
import com.demoProject.SCINEMA.dto.request.UserUpdateRequest;
import com.demoProject.SCINEMA.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
