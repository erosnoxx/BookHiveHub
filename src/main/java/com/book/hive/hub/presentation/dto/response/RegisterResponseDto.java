package com.book.hive.hub.presentation.dto.response;

import java.util.UUID;

public record RegisterResponseDto(
        String message,
        Integer status,
        UUID user_id) {
}