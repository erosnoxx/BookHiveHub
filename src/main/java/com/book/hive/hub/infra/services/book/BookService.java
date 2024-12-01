package com.book.hive.hub.infra.services.book;

import com.book.hive.hub.application.exceptions.NotFoundException;
import com.book.hive.hub.data.book.BookRepository;
import com.book.hive.hub.domain.entities.book.BookEntity;
import com.book.hive.hub.domain.entities.user.UserEntity;
import com.book.hive.hub.presentation.dto.request.book.BookRequestDto;
import com.book.hive.hub.presentation.dto.response.book.BookResponseDto;
import com.book.hive.hub.presentation.dto.response.common.DeleteResponseDto;
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

    public BookResponseDto updateBook(UUID bookId, BookRequestDto data) {
        Optional<BookEntity> book = this.repository.findById(bookId);

        isBookValid(book, bookId);

        BookEntity validBook = book.get();

        if (data.title() != null && !data.title().isBlank())
            validBook.setTitle(data.title());

        if (data.author() != null && !data.author().isBlank())
            validBook.setAuthor(data.author());

        if (data.genre() != null && !data.genre().isBlank())
            validBook.setGenre(data.genre());

        if (data.publicationYear() != null)
            validBook.setPublicationYear(data.publicationYear());

        if (data.status() != null)
            validBook.setStatus(data.status());

        this.repository.save(validBook);

        return new BookResponseDto(
                validBook.getId(),
                validBook.getTitle(),
                validBook.getAuthor(),
                validBook.getGenre(),
                validBook.getPublicationYear(),
                validBook.getStatus()
        );
    }

    public DeleteResponseDto deleteBook(UUID bookId) {
        Optional<BookEntity> book = this.repository.findById(bookId);

        isBookValid(book, bookId);

        BookEntity validBook = book.get();

        this.repository.delete(validBook);

        return new DeleteResponseDto(true,
                "Livro com ID: " + bookId.toString() + " Deletado com sucesso");
    }

    private void isBookValid(Optional<BookEntity> book, UUID bookId) {
        if (book.isEmpty()) throw new NotFoundException(
                "Livro com o ID: " + bookId.toString() + " Não encontrado");
    }
}
