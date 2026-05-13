package com.account.system.controller;

import com.account.system.dto.request.LoginRequestDTO;
import com.account.system.dto.request.UserRegisterDTO;
import com.account.system.dto.response.LoginResponseDTO;
import com.account.system.dto.response.UserResponseDTO;
import com.account.system.entity.Users;
import com.account.system.security.jwt.JwtProvider;
import com.account.system.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService service;
    private final JwtProvider jwtProvider;

    public AccountController(AccountService accountService, JwtProvider jwtProvider) {
        this.service = accountService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO user) {
        UserResponseDTO response = service.register(user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO user) {
        Users login = service.login(user.email(), user.password());

        String token = jwtProvider.generateToken(login);

        LoginResponseDTO response = new LoginResponseDTO(token);

        return ResponseEntity.ok(response);
    }
}
