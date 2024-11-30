package com.book.hive.hub.infra.services;

import com.book.hive.hub.data.RoleRepository;
import com.book.hive.hub.data.UserRepository;
import com.book.hive.hub.domain.entities.RoleEntity;
import com.book.hive.hub.domain.entities.UserEntity;
import com.book.hive.hub.presentation.dto.request.RegisterRequestDto;
import com.book.hive.hub.presentation.dto.response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    public UserResponseDto createUser(RegisterRequestDto data) {
        if (this.repository.findByUsername(data.username()) != null) throw new IllegalArgumentException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        RoleEntity role = this.roleRepository.findByName("USER");
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);

        UserEntity user = new UserEntity();
        user.setUsername(data.username());
        user.setEmail(data.email());
        user.setPassword(encryptedPassword);
        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        user.setBirthDate(data.birthDate());
        user.setRoles(roles);

        UserEntity savedUser = this.repository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getBirthDate(),
                savedUser.getCreatedAt()
        );
    }
}
