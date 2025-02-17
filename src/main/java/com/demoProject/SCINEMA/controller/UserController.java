package com.demoProject.SCINEMA.controller;

import com.demoProject.SCINEMA.dto.reponse.UserResponse;
import com.demoProject.SCINEMA.dto.request.ApiResponse;
import com.demoProject.SCINEMA.dto.request.UserCreationRequest;
import com.demoProject.SCINEMA.dto.request.UserUpdateRequest;
import com.demoProject.SCINEMA.entity.User;
import com.demoProject.SCINEMA.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController
{
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request)
    {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers()
    {
        var authentication =  SecurityContextHolder.getContext().getAuthentication();

        log.info("Username : {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId)
    {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo()
    {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request)
    {
        return userService.updateUser(userId, request);
    }


    @DeleteMapping("{userId}")
    String deleteUser(@PathVariable String userId)
    {
        userService.deleteUser(userId);
        return "User deleted";
    }

}
