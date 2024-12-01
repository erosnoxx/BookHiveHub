package com.book.hive.hub.data.user;

import com.book.hive.hub.domain.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    public UserDetails findByUsername(String username);

    public UserEntity getByUsername(String username);
}
