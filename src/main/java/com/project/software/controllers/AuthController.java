package com.project.software.controllers;

import com.project.software.dto.AuthRequestDTO;
import com.project.software.dto.JwtResponseDTO;
import com.project.software.dto.UserRequestDto;
import com.project.software.exceptions.NotFoundException;
import com.project.software.jwt.JwtServiceImpl;
import com.project.software.models.User;
import com.project.software.services.implementations.UserDetailsServiceImpl;
import com.project.software.services.implementations.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto userRequestDto){
        userService.createUser(userRequestDto);
        return "User Registered Successfully..!!";
    }

    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        return JwtResponseDTO.builder()
                .accessToken(jwtService.generateToken(userDetails))
                .refreshToken(jwtService.generateRefreshToken(userDetails))
                .build();
    }

    @PostMapping("/refresh")
    public JwtResponseDTO refresh(@RequestBody JwtResponseDTO jwtResponseDTO){
        String username = jwtService.extractUsername(jwtResponseDTO.getAccessToken());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return JwtResponseDTO.builder()
                .accessToken(jwtService.generateToken(userDetails))
                .refreshToken(jwtResponseDTO.getRefreshToken())
                .build();
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
