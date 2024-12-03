package com.book.hive.hub.presentation.controller.book;

import com.book.hive.hub.application.useCases.book.*;
import com.book.hive.hub.presentation.dto.request.book.BookRequestDto;
import com.book.hive.hub.presentation.dto.response.book.BookResponseDto;
import com.book.hive.hub.presentation.dto.response.common.DeleteResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private CreateBookUseCase createBookUseCase;
    @Autowired
    private GetAllBooksUseCase getAllBooksUseCase;
    @Autowired
    private GetBookUseCase getBookUseCase;
    @Autowired
    private UpdateBookUseCase updateBookUseCase;
    @Autowired
    private DeleteBookUseCase deleteBookUseCase;


    @PostMapping("")
    public ResponseEntity<BookResponseDto> createBook (
            @Valid @RequestBody BookRequestDto data) {
        BookResponseDto bookResponseDto = this.createBookUseCase.execute(data);
        String location = "/books/" + bookResponseDto.id();

        return ResponseEntity.created(URI.create(location)).body(bookResponseDto);
    }

    @GetMapping("")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> bookResponseDto = this.getAllBooksUseCase.execute();

        return ResponseEntity.ok().body(bookResponseDto);
    }

    @GetMapping("/{book_id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable("book_id") UUID bookId) {
        BookResponseDto bookResponseDto = this.getBookUseCase.execute(bookId);

        return ResponseEntity.ok().body(bookResponseDto);
    }

    @PutMapping("/{book_id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable("book_id") UUID bookId,
            @Valid @RequestBody BookRequestDto data) {
        BookResponseDto bookResponseDto = this.updateBookUseCase.execute(bookId, data);

        return ResponseEntity.ok().body(bookResponseDto);
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<DeleteResponseDto> deleteBook(
            @PathVariable("book_id") UUID bookId) {
        DeleteResponseDto deleteResponseDto = this.deleteBookUseCase.execute(bookId);

        return ResponseEntity.ok().body(deleteResponseDto);
    }
}
