package com.book.hive.hub.application.useCases.book;

import com.book.hive.hub.infra.services.book.BookService;
import com.book.hive.hub.presentation.dto.request.book.BookRequestDto;
import com.book.hive.hub.presentation.dto.response.book.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBookUseCase {
    @Autowired
    private BookService bookService;

    public BookResponseDto execute(BookRequestDto data) {
        return this.bookService.createBook(data);
    }
}
