package com.book.hive.hub.data.book;

import com.book.hive.hub.domain.entities.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {
}
