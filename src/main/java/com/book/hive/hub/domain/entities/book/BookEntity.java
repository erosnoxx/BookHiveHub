package com.book.hive.hub.domain.entities.book;

import com.book.hive.hub.domain.entities.common.BaseEntity;
import com.book.hive.hub.domain.enums.BookStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity extends BaseEntity {
    @Column(length = 100, nullable = false, unique = true)
    @NotNull
    private String title;

    @Column(length = 50, nullable = false)
    @NotNull
    private String author;

    @Column(length = 50, nullable = false)
    @NotNull
    private String genre;

    @Column(name = "publication_year", nullable = false)
    @NotNull
    private Integer publicationYear;

    @Column
    @NotNull
    private BookStatus status;
}
