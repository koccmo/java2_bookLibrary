SELECT * FROM readers
INNER JOIN reader_books
ON readers.id=reader_books.reader_id
WHERE id=1002 AND book_return_date IS NULL;

SELECT first_name,last_name FROM readers
INNER JOIN reader_books
ON readers.id=reader_books.reader_id
WHERE reader_books.book_id=1;

SELECT title FROM books
INNER JOIN reader_books
ON books.id = reader_books.book_id
GROUP BY books.id;