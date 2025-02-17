package com.demoProject.SCINEMA.service;

import com.demoProject.SCINEMA.dto.reponse.UserResponse;
import com.demoProject.SCINEMA.dto.request.UserCreationRequest;
import com.demoProject.SCINEMA.dto.request.UserUpdateRequest;
import com.demoProject.SCINEMA.entity.Users;
import com.demoProject.SCINEMA.exception.AppException;
import com.demoProject.SCINEMA.exception.ErrorCode;
import com.demoProject.SCINEMA.mapper.UserMapper;
import com.demoProject.SCINEMA.repository.RoleRepository;
import com.demoProject.SCINEMA.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request) {
        // Kiểm tra xem tên người dùng đã tồn tại chưa
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        // Sử dụng mapper để chuyển đổi UserCreationRequest thành User entity
        Users user = userMapper.toUser(request);

        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Chuyển đổi User entity thành UserResponse và trả về
        return userMapper.toUserResponse(userRepository.save(user));
    }


    public List<Users> getUsers()
    {
        return userRepository.findAll();
    }

    public UserResponse getUser(String id)
    {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!")));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request)
    {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var role = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(role));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id)
    {
        userRepository.deleteById(id);
    }
}
