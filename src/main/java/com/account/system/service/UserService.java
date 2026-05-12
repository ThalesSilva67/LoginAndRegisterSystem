package com.account.system.service;

import com.account.system.configuration.SecurityConfigurations;
import com.account.system.dto.request.UserRegisterDTO;
import com.account.system.dto.response.UserResponseDTO;
import com.account.system.entity.Users;
import com.account.system.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, SecurityConfigurations security, PasswordEncoder encoder) {
        repository = userRepository;
        this.encoder = encoder;
    }

    public UserResponseDTO register(UserRegisterDTO userRegisterDTO) {
        boolean present = repository.findByEmail(userRegisterDTO.email()).isPresent();

        if(present) throw new RuntimeException("Email already exists");

        Users user = new Users();
        user.setName(userRegisterDTO.name());
        user.setEmail(userRegisterDTO.email());
        user.setPassword(encoder.encode(userRegisterDTO.password()));

        Users savedUser = repository.save(user);

        return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

}
