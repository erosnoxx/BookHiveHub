package com.book.hive.hub.domain.entities.Common;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "createdat", updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
