package com.book.hive.hub.presentation.controller.user;

import com.book.hive.hub.application.useCases.user.DeleteUserUseCase;
import com.book.hive.hub.application.useCases.user.GetAllUsersUseCase;
import com.book.hive.hub.application.useCases.user.GetUserUseCase;
import com.book.hive.hub.application.useCases.user.UpdateUserUseCase;
import com.book.hive.hub.presentation.dto.request.authentication.RegisterRequestDto;
import com.book.hive.hub.presentation.dto.response.common.DeleteResponseDto;
import com.book.hive.hub.presentation.dto.response.user.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private GetAllUsersUseCase getAllUsersUseCase;
    @Autowired
    private GetUserUseCase getUserUseCase;
    @Autowired
    private UpdateUserUseCase updateUserUseCase;
    @Autowired
    private DeleteUserUseCase deleteUserUseCase;

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> users() {
        List<UserResponseDto> users = this.getAllUsersUseCase.execute();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponseDto> getUser(
            @PathVariable("user_id") UUID userId) {
        UserResponseDto user = this.getUserUseCase.execute(userId);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable("user_id") UUID userId,
            @Valid @RequestBody RegisterRequestDto data) {

        UserResponseDto user = this.updateUserUseCase.execute(userId, data);

        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<DeleteResponseDto> deleteUser(
            @PathVariable("user_id") UUID userId) {
        DeleteResponseDto deleteResponseDto = this.deleteUserUseCase.execute(userId);

        return ResponseEntity.ok().body(deleteResponseDto);
    }
}
