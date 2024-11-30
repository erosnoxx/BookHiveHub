package com.book.hive.hub.presentation.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String username,
        String email,
        String firstName,
        String lastName,
        LocalDate birthDate,
        LocalDateTime createdAt) {
}
