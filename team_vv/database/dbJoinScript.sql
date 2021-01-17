
1) Task 18

SELECT title, author FROM books
	INNER JOIN reader_books
		ON books.id = reader_books.book_id
WHERE book_return_date IS null
AND reader_id = 1002;


2) Task 19

SELECT first_name, last_name FROM readers
	INNER JOIN reader_books
		ON readers.id = reader_books.reader_id
WHERE book_id = 1002;

3) Task 20

SELECT book_id, title, author, COUNT(book_out_date) as POPULARITY FROM reader_books
	JOIN books
		ON book_id = books.id
group by book_id
order by POPULARITY DESC;

