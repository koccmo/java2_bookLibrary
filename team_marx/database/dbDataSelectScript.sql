SELECT * FROM books;
SELECT * FROM readers;
SELECT * FROM reader_books;



SELECT * FROM books
WHERE id = 1;

SELECT * FROM readers
WHERE id = 2;

SELECT * FROM reader_books
WHERE id = 2;



SELECT * FROM books
WHERE title = "Title 2";

SELECT * FROM readers
WHERE first_name = "FirstName 2";

SELECT * FROM reader_books
WHERE book_out_date = "2021-01-02 09:00:00";



SELECT * FROM books
WHERE id > 5
   AND page_count < 22
   OR title = "Title 2";

SELECT * FROM readers
WHERE id > 2
    AND first_name = "FirstName 3"
    OR last_name = "LastName 11";

SELECT * FROM reader_books
WHERE id > 5
    AND book_out_date < "2021-01-11 09:00:00"
    OR book_return_date = "2021-01-22 09:00:00";



SELECT * FROM books
WHERE id > 5
ORDER BY author DESC;

SELECT * FROM readers
WHERE id > 5
ORDER BY last_name DESC;

SELECT * FROM reader_books
WHERE id > 5
ORDER BY book_out_date DESC;



SELECT * FROM books
WHERE id > 5
ORDER BY author DESC LIMIT 3;

SELECT * FROM readers
WHERE id > 5
ORDER BY last_name DESC LIMIT 2;

SELECT * FROM reader_books
WHERE id > 5
ORDER BY book_out_date DESC LIMIT 3;



SELECT * FROM books
WHERE id > 5
ORDER BY author DESC
LIMIT 3 OFFSET 2;

SELECT * FROM readers
WHERE id > 5
ORDER BY last_name DESC
LIMIT 1 OFFSET 1;

SELECT * FROM reader_books
WHERE id > 5
ORDER BY book_out_date DESC
LIMIT 3 OFFSET 2;



SELECT * FROM books
WHERE description IS NOT NULL;

SELECT * FROM readers
WHERE last_name LIKE "LastName 1%";

SELECT * FROM reader_books
WHERE book_return_date IS NULL
    AND book_out_date < "2021-01-05 09:00:00"
ORDER BY book_out_date ASC;