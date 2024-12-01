package com.book.hive.hub.application.useCases.authentication;

import com.book.hive.hub.infra.services.user.UserService;
import com.book.hive.hub.presentation.dto.request.authentication.RegisterRequestDto;
import com.book.hive.hub.presentation.dto.response.authentication.RegisterResponseDto;
import com.book.hive.hub.presentation.dto.response.user.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUseCase {
    @Autowired
    private UserService userService;

    public RegisterResponseDto execute(RegisterRequestDto data) {
        UserResponseDto user = this.userService.createUser(data);

        return new RegisterResponseDto(
                "Usuário criado",
                201,
                user.id());
    }
}
