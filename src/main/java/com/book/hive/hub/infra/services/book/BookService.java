package com.book.hive.hub.infra.services.book;

import com.book.hive.hub.data.book.BookRepository;
import com.book.hive.hub.domain.entities.book.BookEntity;
import com.book.hive.hub.presentation.dto.request.book.BookRequestDto;
import com.book.hive.hub.presentation.dto.response.book.BookResponseDto;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public BookResponseDto createBook (BookRequestDto data) {
        if (this.repository.findByTitle(data.title()).isPresent())
            throw new IllegalArgumentException();

        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(data.title());
        bookEntity.setAuthor(data.author());
        bookEntity.setGenre(data.genre());
        bookEntity.setPublicationYear(data.publicationYear());
        bookEntity.setStatus(data.status());

        this.repository.save(bookEntity);

        return new BookResponseDto(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getGenre(),
                bookEntity.getPublicationYear(),
                bookEntity.getStatus()
        );
    }

    public List<BookResponseDto> getAllBooks () {
        List<BookEntity> bookEntities = this.repository.findAll();

        return bookEntities.stream().map(bookEntity -> new BookResponseDto(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getGenre(),
                bookEntity.getPublicationYear(),
                bookEntity.getStatus()
        )).collect(Collectors.toList());
    }

    public BookResponseDto getBook(UUID bookId) {
        Optional<BookEntity> bookEntity = this.repository.findById(bookId);

        isBookValid(bookEntity, bookId);

        BookEntity validBook = bookEntity.get();

        return new BookResponseDto(
                validBook.getId(),
                validBook.getTitle(),
                validBook.getAuthor(),
                validBook.getGenre(),
                validBook.getPublicationYear(),
                validBook.getStatus()
        );
    }

    private void isBookValid(Optional<BookEntity> book, UUID bookId) {
        if (book.isEmpty()) throw new OpenApiResourceNotFoundException(
                "Livro com o ID: " + bookId.toString() + " Não encontrado");
    }
}
