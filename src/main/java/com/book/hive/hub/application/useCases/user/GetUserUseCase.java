package com.book.hive.hub.application.useCases.user;

import com.book.hive.hub.infra.services.user.UserService;
import com.book.hive.hub.infra.utils.validation.ValidatorUtil;
import com.book.hive.hub.presentation.dto.response.user.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserUseCase {
    @Autowired
    private UserService userService;

    public UserResponseDto execute(UUID userId) {
        UserResponseDto user = this.userService.getUser(userId);

        if (!ValidatorUtil.isUserAuthorized(user)) {
            throw new SecurityException("Você não tem permissão para acessar esse recurso");
        }

        return user;
    }
}
