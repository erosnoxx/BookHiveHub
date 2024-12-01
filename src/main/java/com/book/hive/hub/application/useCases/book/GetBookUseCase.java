package com.book.hive.hub.application.useCases.book;

import com.book.hive.hub.infra.services.book.BookService;
import com.book.hive.hub.presentation.dto.response.book.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetBookUseCase {
    @Autowired
    private BookService bookService;

    public BookResponseDto execute(UUID bookId) {
        return this.bookService.getBook(bookId);
    }
}
