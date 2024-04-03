package com.project.software.services.implementations;

import com.project.software.dto.UserRequestDto;
import com.project.software.enums.Role;
import com.project.software.models.User;
import com.project.software.repositories.UserRepository;
import com.project.software.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void createUser(UserRequestDto userRequestDto) {
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .role(Role.valueOf(userRequestDto.getRole()))
                .email(userRequestDto.getEmail())
                .active(true)
                .registered(true)
                .build();
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
