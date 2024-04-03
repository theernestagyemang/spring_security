package com.project.software.services.interfaces;

import com.project.software.dto.UserRequestDto;
import com.project.software.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void createUser(UserRequestDto userRequestDto);
    List<User> getAllUsers();
}
