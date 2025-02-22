package com.demoProject.Scinema.mapper;


import com.demoProject.Scinema.dto.reponse.UserResponse;
import com.demoProject.Scinema.dto.request.UserCreationRequest;
import com.demoProject.Scinema.dto.request.UserUpdateRequest;
import com.demoProject.Scinema.entity.User;
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
