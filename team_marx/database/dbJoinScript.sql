-- Task_18
SELECT DISTINCT title, author FROM books
INNER JOIN reader_books
	ON books.id = reader_books.book_id
WHERE reader_books.reader_id = 2
AND reader_books.book_return_date IS NULL
ORDER BY title ASC, author ASC;

-- Task_19
SELECT DISTINCT first_name, last_name FROM readers
INNER JOIN reader_books
	ON readers.id = reader_books.reader_id
WHERE reader_books.book_id = 2
ORDER BY first_name ASC, last_name ASC;

-- Task_20
SELECT title, author FROM books
INNER JOIN reader_books
	ON books.id = reader_books.book_id
GROUP BY books.id
ORDER BY COUNT(books.id) DESC;