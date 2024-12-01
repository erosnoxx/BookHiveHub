package com.book.hive.hub.application.useCases.book;

import com.book.hive.hub.infra.services.book.BookService;
import com.book.hive.hub.infra.services.user.UserService;
import com.book.hive.hub.presentation.dto.response.book.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllBooksUseCase {
    @Autowired
    private BookService bookService;

    public List<BookResponseDto> execute() {
        return this.bookService.getAllBooks();
    }
}
