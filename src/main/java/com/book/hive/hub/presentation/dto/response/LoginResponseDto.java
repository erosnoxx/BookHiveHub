package com.book.hive.hub.presentation.dto.response;

import java.time.Instant;

public record LoginResponseDto(
        String token,
        Instant expiresAt) {
}
