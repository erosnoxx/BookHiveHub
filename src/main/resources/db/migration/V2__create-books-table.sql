CREATE TABLE books (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(100) NOT NULL UNIQUE,
    author VARCHAR(50) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    publication_year INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL,
    createdat TIMESTAMP NOT NULL DEFAULT now(),
    updatedat TIMESTAMP
);
