package com.account.system.controller;

import com.account.system.dto.request.LoginRequestDTO;
import com.account.system.dto.request.UserRegisterDTO;
import com.account.system.dto.response.LoginResponseDTO;
import com.account.system.dto.response.UserResponseDTO;
import com.account.system.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO user) {
        UserResponseDTO response = service.register(user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO user) {
        LoginResponseDTO response = service.login(user.email(), user.password());

        return ResponseEntity.ok(response);
    }
}
