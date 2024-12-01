package com.book.hive.hub.presentation.dto.request.authentication;

import com.book.hive.hub.domain.enums.UserRole;

import java.time.LocalDate;

public record RegisterRequestDto(
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        LocalDate birthDate,
        UserRole role) {
}
