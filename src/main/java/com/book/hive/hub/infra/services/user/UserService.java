package com.book.hive.hub.infra.services.user;

import com.book.hive.hub.application.exceptions.NotFoundException;
import com.book.hive.hub.data.user.UserRepository;
import com.book.hive.hub.data.user.WishListRepository;
import com.book.hive.hub.domain.entities.user.UserEntity;
import com.book.hive.hub.domain.entities.user.WishListEntity;
import com.book.hive.hub.presentation.dto.request.authentication.RegisterRequestDto;
import com.book.hive.hub.presentation.dto.response.common.DeleteResponseDto;
import com.book.hive.hub.presentation.dto.response.user.UserResponseDto;
import org.apache.catalina.User;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private WishListRepository wishListRepository;

    public UserResponseDto createUser(RegisterRequestDto data) {
        if (this.repository.findByUsername(data.username()) != null) throw new IllegalArgumentException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UserEntity user = new UserEntity();
        user.setUsername(data.username());
        user.setEmail(data.email());
        user.setPassword(encryptedPassword);
        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        user.setBirthDate(data.birthDate());
        user.setRole(data.role());

        UserEntity savedUser = this.repository.save(user);

        WishListEntity wishListEntity = new WishListEntity();
        wishListEntity.setUser(savedUser);
        wishListEntity.setBooks(new ArrayList<>());

        this.wishListRepository.save(wishListEntity);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getBirthDate(),
                savedUser.getRole().getRole(),
                savedUser.getCreatedAt()
        );
    }

    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> userEntities = this.repository.findAll();
        return userEntities.stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getBirthDate(),
                        user.getRole().getRole(),
                        user.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public UserResponseDto getUser(UUID userId) {
        Optional<UserEntity> user = this.repository.findById(userId);

        isUserEmpty(user, userId);

        UserEntity validUser = user.get();

        return new UserResponseDto(
                validUser.getId(),
                validUser.getUsername(),
                validUser.getEmail(),
                validUser.getFirstName(),
                validUser.getLastName(),
                validUser.getBirthDate(),
                validUser.getRole().getRole(),
                validUser.getCreatedAt()
        );
    }

    public UserResponseDto updateUser(UUID userId, RegisterRequestDto data) {
        Optional<UserEntity> user = this.repository.findById(userId);

        isUserEmpty(user, userId);

        UserEntity validUser = user.get();

        if (data.username() != null && !data.username().isBlank())
            validUser.setUsername(data.username());

        if (data.email() != null && !data.email().isBlank())
            validUser.setEmail(data.email());

        if (data.password() != null && !data.password().isBlank()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            validUser.setPassword(encryptedPassword);
        }

        if (data.firstName() != null && !data.firstName().isBlank())
            validUser.setFirstName(data.firstName());

        if (data.lastName() != null && !data.lastName().isBlank())
            validUser.setLastName(data.lastName());

        if (data.birthDate() != null)
            validUser.setBirthDate(data.birthDate());

        if (data.role() != null)
            validUser.setRole(data.role());

        this.repository.save(validUser);

        return new UserResponseDto(
                validUser.getId(),
                validUser.getUsername(),
                validUser.getEmail(),
                validUser.getFirstName(),
                validUser.getLastName(),
                validUser.getBirthDate(),
                validUser.getRole().getRole(),
                validUser.getCreatedAt()
        );
    }

    public DeleteResponseDto deleteUser(UUID userId) {
        Optional<UserEntity> user = this.repository.findById(userId);

        isUserEmpty(user, userId);

        UserEntity validUser = user.get();

        this.repository.delete(validUser);

        return new DeleteResponseDto(true,
                "Usuário com ID: " + userId.toString() + " Deletado com sucesso");
    }

    private void isUserEmpty(Optional<UserEntity> user, UUID userId) {
        if (user.isEmpty()) throw new NotFoundException(
                "Usuário com o ID: " + userId.toString() + " Não encontrado");
    }
}
