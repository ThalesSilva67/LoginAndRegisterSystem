package com.account.system.service;

import com.account.system.dto.request.UserRegisterDTO;
import com.account.system.dto.response.UserResponseDTO;
import com.account.system.entity.Users;
import com.account.system.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        repository = userRepository;
    }

    public UserResponseDTO register(UserRegisterDTO userRegisterDTO) {
        boolean present = repository.findByEmail(userRegisterDTO.email()).isPresent();

        if(present) throw new RuntimeException("Email already exists");

        Users user = new Users();
        user.setName(userRegisterDTO.name());
        user.setEmail(userRegisterDTO.email());
        user.setPassword(userRegisterDTO.password());

        Users savedUser = repository.save(user);

        return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

}
