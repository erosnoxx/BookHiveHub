package com.book.hive.hub.application.useCases.user;

import com.book.hive.hub.infra.services.user.UserService;
import com.book.hive.hub.presentation.dto.response.user.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersUseCase {
    @Autowired
    private UserService userService;

    public List<UserResponseDto> execute() {
        return this.userService.getAllUsers();
    }
}
