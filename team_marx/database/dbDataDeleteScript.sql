DELETE FROM reader_books;
DELETE FROM books;
DELETE FROM readers;

DELETE FROM reader_books WHERE id = 2;
DELETE FROM books WHERE id = 2;
DELETE FROM readers WHERE id = 2;

DELETE FROM reader_books WHERE reader_id > 3;
DELETE FROM books WHERE id > 3 AND description IS NULL;
DELETE FROM readers WHERE first_name = "FirstName 10" OR last_name = "LastName 12";
