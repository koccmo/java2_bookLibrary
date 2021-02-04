INSERT INTO books(bookTitle,bookAuthor,bookPrice,yearOfBookIssue)
VALUES("aaa","aaa",10.00,2001);

INSERT INTO books(bookTitle,bookAuthor,bookPrice,yearOfBookIssue)
VALUES("bbb","bbb",11.00,2001);

INSERT INTO books(id,bookTitle,bookAuthor,bookPrice,yearOfBookIssue)
VALUES(3,"ccc","ccc",12.00,2002);

INSERT INTO books(id,bookTitle,bookAuthor,bookPrice,yearOfBookIssue)
VALUES(4,"ddd","ddd",13.00,2002);

INSERT INTO books(id,bookTitle,bookAuthor,bookPrice,yearOfBookIssue,page_count,description)
VALUES(5,"eee","eee",14.00,2003,100,"description");

INSERT INTO books(id,bookTitle,bookAuthor,bookPrice,yearOfBookIssue,page_count,description)
VALUES(6,"fff","fff",15.00,2003,200,"description");

INSERT INTO readers(first_name,last_name)
VALUES("aaa","aaa");

INSERT INTO readers(first_name,last_name)
VALUES("bbb","bbb");

INSERT INTO readers(id,first_name,last_name)
VALUES(3,"ccc","ccc");

INSERT INTO readers(id,first_name,last_name)
VALUES(4,"ddd","ddd");

INSERT INTO reader_books(reader_id,book_id,book_out_date)
VALUES(1,1,'2021-01-10 23:59:59');

INSERT INTO reader_books(reader_id,book_id,book_out_date)
VALUES(2,2,'2021-01-11 23:59:59');
