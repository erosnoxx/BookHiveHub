package com.book.hive.hub.domain.enums;


public enum UserRole {
    ADMIN("admin"),
    USER("user");

    final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
