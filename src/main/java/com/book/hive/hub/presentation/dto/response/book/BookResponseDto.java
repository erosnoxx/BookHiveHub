package com.book.hive.hub.presentation.dto.response.book;

import com.book.hive.hub.domain.enums.BookStatus;

import java.util.UUID;

public record BookResponseDto(
        UUID id,
        String title,
        String author,
        String genre,
        Integer publicationYear,
        BookStatus status) {
}
