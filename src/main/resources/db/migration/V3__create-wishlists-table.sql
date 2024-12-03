CREATE TABLE wishlists (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE wishlist_books (
    wishlist_id UUID NOT NULL,
    book_id UUID NOT NULL,
    PRIMARY KEY (wishlist_id, book_id),
    FOREIGN KEY (wishlist_id) REFERENCES wishlists(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);
