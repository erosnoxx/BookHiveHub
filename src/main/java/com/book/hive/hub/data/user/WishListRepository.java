package com.book.hive.hub.data.user;

import com.book.hive.hub.domain.entities.user.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WishListRepository extends JpaRepository<WishListEntity, UUID> {
}
