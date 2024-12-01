package com.book.hive.hub.presentation.dto.request.book;

import com.book.hive.hub.domain.enums.BookStatus;

import java.util.UUID;

public record BookRequestDto(
        String title,
        String author,
        String genre,
        Integer publicationYear,
        BookStatus status) {
}
