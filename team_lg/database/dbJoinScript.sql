SELECT * FROM reader
INNER JOIN reader_books
ON reader.id=1
WHERE reader_id=1 and book_return_date IS NULL;

SELECT first_name,last_name FROM reader
INNER JOIN reader_books
ON reader.id=reader_books.reader_id
WHERE reader_books.book_id=1;

SELECT bookTitle,bookAuthor FROM book
INNER JOIN reader_books
ON book.id = reader_books.book_id
GROUP BY book.id;