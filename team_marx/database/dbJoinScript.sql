-- Lesson_9___Task_18
SELECT DISTINCT title, author FROM books
INNER JOIN reader_books
	ON books.id = reader_books.book_id
WHERE reader_books.reader_id = 2
AND reader_books.book_return_date IS NULL
ORDER BY title ASC, author ASC;

-- Lesson_9___Task_19
SELECT DISTINCT first_name, last_name FROM readers
INNER JOIN reader_books
	ON readers.id = reader_books.reader_id
WHERE reader_books.book_id = 2
ORDER BY first_name ASC, last_name ASC;

-- Lesson_9___Task_20
SELECT title, author FROM books
INNER JOIN reader_books
	ON books.id = reader_books.book_id
GROUP BY books.id
ORDER BY COUNT(books.id) DESC;

-- Lesson_12___Task_5
SELECT reader_books.*, readers.*, books.*
FROM reader_books
LEFT JOIN readers
	ON reader_books.reader_id = readers.id
LEFT JOIN books
	ON reader_books.reader_id = books.id
WHERE reader_books.id = 4;