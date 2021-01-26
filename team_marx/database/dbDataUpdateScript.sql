UPDATE books
SET title = "Title for all",
    author = "Author for all",
    page_count = 100,
    description = "Description for all"
WHERE id > 0;

UPDATE readers
SET first_name = "FirstName for all",
    last_name = "LastName for all"
WHERE id > 0;

UPDATE reader_books
SET reader_id = 1, book_id = 1,
    book_out_date = "2021-01-01 01:01:01",
    book_return_date = "2021-11-11 11:11:11"
WHERE id > 0;



UPDATE books
SET title = "Title Updated by id",
    author = "Author Updated by id",
    page_count = 200,
    description = "Description Updated by id"
WHERE id = 2;

UPDATE readers
SET first_name = "FirstName Updated by id",
    last_name = "LastName Updated by id"
WHERE id = 2;

UPDATE reader_books
SET reader_id = 2,
    book_id = 2,
    book_out_date = "2021-02-02 02:02:02",
    book_return_date = "2021-12-22 22:22:22"
WHERE id = 2;



UPDATE books
SET title = "Title Changed"
WHERE id = 3;

UPDATE readers
SET first_name = "FirstName Changed"
WHERE id = 3;

UPDATE reader_books
SET reader_id = 3
WHERE id = 3;



UPDATE books
SET author = "Author Changed By page count"
WHERE page_count = 200;

UPDATE readers
SET last_name = "LastName Changed by FirstName"
WHERE first_name = "FirstName Changed";

UPDATE reader_books
SET book_id = 11
WHERE book_return_date = "2021-12-22 22:22:22";