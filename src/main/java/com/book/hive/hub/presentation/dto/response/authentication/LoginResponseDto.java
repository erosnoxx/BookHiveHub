package com.book.hive.hub.presentation.dto.response.authentication;

import java.time.Instant;

public record LoginResponseDto(
        String token,
        Instant expiresAt) {
}
