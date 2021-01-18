DELETE FROM book
WHERE id=3;

DELETE FROM reader_books;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM book
WHERE bookTitle='new title';

DELETE FROM book;