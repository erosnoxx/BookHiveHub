package com.book.hive.hub.presentation.dto.response.common;

public record ErrorResponseDto(
        String message,
        Integer status,
        String details) {
}
