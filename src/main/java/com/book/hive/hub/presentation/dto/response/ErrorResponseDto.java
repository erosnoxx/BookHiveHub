package com.book.hive.hub.presentation.dto.response;

public record ErrorResponseDto(
        String message,
        Integer status,
        String details) {
}
