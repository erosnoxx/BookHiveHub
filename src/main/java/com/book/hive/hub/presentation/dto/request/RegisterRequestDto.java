package com.book.hive.hub.presentation.dto.request;

import java.time.LocalDate;

public record RegisterRequestDto(
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        LocalDate birthDate) {
}
