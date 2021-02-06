SELECT *
FROM `books`;

SELECT *
FROM `readers`;

SELECT *
FROM `reader_books`;

SELECT * FROM books
WHERE id=3;

SELECT id FROM books
WHERE bookTitle='ddd';

SELECT id FROM books
WHERE bookAuthor='ccc';

SELECT * FROM books
WHERE bookTitle='eee' AND bookAuthor='eee';

SELECT * FROM books
WHERE bookTitle='aaa' OR bookAuthor='aaa';

SELECT * FROM books
ORDER BY id ASC;

SELECT * FROM books
ORDER BY id DESC;

SELECT * FROM books
ORDER BY description ASC;

SELECT * FROM books
ORDER BY description DESC;

SELECT * FROM books
WHERE bookTitle LIKE 'f%'
LIMIT 2;

SELECT * FROM books
WHERE description LIKE 'desc%'
LIMIT 2;
