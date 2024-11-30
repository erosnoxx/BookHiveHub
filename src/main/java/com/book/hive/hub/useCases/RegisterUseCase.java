package com.book.hive.hub.useCases;

import com.book.hive.hub.infra.services.UserService;
import com.book.hive.hub.presentation.dto.request.RegisterRequestDto;
import com.book.hive.hub.presentation.dto.response.RegisterResponseDto;
import com.book.hive.hub.presentation.dto.response.UserResponseDto;
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
