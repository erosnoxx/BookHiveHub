package com.book.hive.hub.data;

import com.book.hive.hub.domain.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    public RoleEntity findByName(String name);
}
