package com.book.hive.hub.application.useCases.book;

import com.book.hive.hub.infra.services.book.BookService;
import com.book.hive.hub.presentation.dto.response.common.DeleteResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteBookUseCase {
    @Autowired
    private BookService bookService;

    public DeleteResponseDto execute(UUID bookId) {
        return this.bookService.deleteBook(bookId);
    }
}
