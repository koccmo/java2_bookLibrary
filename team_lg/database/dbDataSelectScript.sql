SELECT *
FROM `book`;

SELECT *
FROM `reader`;

SELECT *
FROM `reader_books`;

SELECT * FROM book
WHERE id=3;

SELECT id FROM book
WHERE bookTitle='ddd';

SELECT id FROM book
WHERE bookAuthor='ccc';

SELECT * FROM book
WHERE bookTitle='eee' AND bookAuthor='eee';

SELECT * FROM book
WHERE bookTitle='aaa' OR bookAuthor='aaa';

SELECT * FROM book
ORDER BY id ASC;

SELECT * FROM book
ORDER BY id DESC;

SELECT * FROM book
ORDER BY description ASC;

SELECT * FROM book
ORDER BY description DESC;

SELECT * FROM book
WHERE bookTitle LIKE 'f%'
LIMIT 2;

SELECT * FROM book
WHERE description LIKE 'desc%'
LIMIT 2;
