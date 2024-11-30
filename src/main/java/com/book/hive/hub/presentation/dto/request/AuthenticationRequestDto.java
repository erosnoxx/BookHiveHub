package com.book.hive.hub.presentation.dto.request;

public record AuthenticationRequestDto(
        String username,
        String password) {
}
