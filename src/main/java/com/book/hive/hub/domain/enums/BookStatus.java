package com.book.hive.hub.domain.enums;

public enum BookStatus {
    AVALIABLE("avaliable"),
    BORROWED("borrowed"),
    RESERVED("reserved"),
    REMOVED("removed");

    final String status;

    BookStatus(String status) {
        this.status = status;
    }

    public String getStatus()  {
        return status;
    }
}
