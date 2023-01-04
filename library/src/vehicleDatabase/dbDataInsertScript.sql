--INNER	JOIN	Возвращает записи которые имеют соответствие в обеих таблицах
--OUTER	LEFT JOIN	Возвращает все записи из левой таблицы + записи которые имеют соответствие из правой
--OUTER	RIGHT JOIN	Возвращает все записи из правой таблицы + записи которые имеют соответствие из левой
--OUTER	FULL JOIN	Возвращает все записи из обеих таблиц




INSERT INTO books(title, author)
VALUES ("Garden", "John Smith");

INSERT INTO books(id, title, author)
VALUES (2002, "Hope", "James Smith");

INSERT INTO books(id, title, author)
VALUES (3003, "Porch", "Alex Pink");

SELECT * FROM books;


INSERT INTO readers(first_name, last_name)
VALUES ("Dean", "Higgins");

INSERT INTO readers(id, first_name, last_name)
VALUES (2004, "Colin", "Molin");

INSERT INTO readers(id, first_name, last_name)
VALUES (2008, "Ben", "Morgan");

SELECT * FROM readers;


INSERT INTO reader_books(reader_id, book_id, book_out_date)
VALUES (2008, 3003, "2002-12-27 22:22:11");

INSERT INTO reader_books(id, reader_id, book_id, book_out_date)
VALUES (2777, 2004, 2002, NOW());

INSERT INTO reader_books(id, reader_id, book_id, book_out_date, book_return_date)
VALUES (2888, 1002, 1002, "2022-12-11 22:12:11", NOW());

SELECT * FROM reader_books;


SELECT * FROM books;
SELECT title FROM books WHERE id = 1002;
SELECT * FROM books WHERE id = 2002;
SELECT id, title FROM books WHERE author = "Alex Pink";
SELECT id FROM books WHERE title = "Porch" AND author = "Alex Pink";
SELECT * FROM books WHERE title = "Porch" OR id = 2002;
SELECT * FROM books;
SELECT * FROM books ORDER BY id DESC;
SELECT id, id - 1000 AS ID_Minus_1000 FROM books;
SELECT id, id - 1000 AS ID_Minus_1000 FROM books ORDER BY ID_Minus_1000 DESC;
SELECT id, id - 1000 AS ID_Minus_1000 FROM books ORDER BY ID_Minus_1000 DESC LIMIT 2;
SELECT * FROM books LIMIT 1;

SELECT * FROM readers;
SELECT * FROM readers WHERE id = 2004;
SELECT * FROM readers WHERE first_name = "Ben";
SELECT * FROM readers WHERE id = 2004 AND last_name = "Molin";
SELECT * FROM readers WHERE id = 2004 OR last_name = "Morgan";
SELECT first_name FROM readers ORDER BY first_name;
SELECT first_name FROM readers ORDER BY first_name LIMIT 1;


SELECT * FROM reader_books;
SELECT * FROM reader_books WHERE id=2888;
SELECT book_out_date FROM reader_books WHERE book_id = 3003;
SELECT * FROM reader_books WHERE id=2888 AND book_id = 1002;
SELECT * FROM reader_books WHERE id=2888 OR book_id = 3003;
SELECT * FROM reader_books ORDER BY book_out_date ASC;
SELECT * FROM reader_books ORDER BY book_out_date ASC LIMIT 2;