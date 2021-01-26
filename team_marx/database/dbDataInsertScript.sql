INSERT INTO books (title, author)
VALUES ("Title 1", "Author 1");

INSERT INTO books (title, author)
VALUES ("Title 2", "Author 2");

INSERT INTO books (title, author)
VALUES ("Title 3", "Author 3");

INSERT INTO books (id, title, author)
VALUES (10, "Title 10", "Author 10");

INSERT INTO books (id, title, author)
VALUES (11, "Title 11", "Author 11");

INSERT INTO books (id, title, author)
VALUES (12, "Title 12", "Author 12");

INSERT INTO books (id, title, author, page_count, description)
VALUES (20, "Title 20", "Author 20", 20, "Description 20");

INSERT INTO books (id, title, author, page_count, description)
VALUES (21, "Title 21", "Author 21", 21, "Description 21");

INSERT INTO books (id, title, author, page_count, description)
VALUES (22, "Title 22", "Author 22", 22, "Description 22");


INSERT INTO readers (first_name, last_name)
VALUES ("FirstName 1", "LastName 1");

INSERT INTO readers (first_name, last_name)
VALUES ("FirstName 2", "LastName 2");

INSERT INTO readers (first_name, last_name)
VALUES ("FirstName 3", "LastName 3");

INSERT INTO readers (id, first_name, last_name)
VALUES (10, "FirstName 10", "LastName 10");

INSERT INTO readers (id, first_name, last_name)
VALUES (11, "FirstName 11", "LastName 11");

INSERT INTO readers (id, first_name, last_name)
VALUES (12, "FirstName 12", "LastName 12");


INSERT INTO reader_books (reader_id, book_id, book_out_date)
VALUES (1, 1, "2021-01-01 09:00:00");

INSERT INTO reader_books (reader_id, book_id, book_out_date)
VALUES (2, 2, "2021-01-02 09:00:00");

INSERT INTO reader_books (reader_id, book_id, book_out_date)
VALUES (3, 3, "2021-01-03 09:00:00");

INSERT INTO reader_books (id, reader_id, book_id, book_out_date)
VALUES (10, 10, 10, "2021-01-10 09:00:00");

INSERT INTO reader_books (id, reader_id, book_id, book_out_date)
VALUES (11, 11, 11, "2021-01-11 09:00:00");

INSERT INTO reader_books (id, reader_id, book_id, book_out_date)
VALUES (12, 12, 12, "2021-01-12 09:00:00");

INSERT INTO reader_books (id, reader_id, book_id, book_out_date, book_return_date)
VALUES (20, 10, 20, "2021-01-10 09:00:00", "2021-01-20 09:00:00");

INSERT INTO reader_books (id, reader_id, book_id, book_out_date, book_return_date)
VALUES (21, 11, 21, "2021-01-11 09:00:00", "2021-01-21 09:00:00");

INSERT INTO reader_books (id, reader_id, book_id, book_out_date, book_return_date)
VALUES (22, 12, 22, "2021-01-12 09:00:00", "2021-01-22 09:00:00");