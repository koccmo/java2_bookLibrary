DELETE FROM books
WHERE id=3;

DELETE FROM reader_books;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM books
WHERE bookTitle='new title';

DELETE FROM books;