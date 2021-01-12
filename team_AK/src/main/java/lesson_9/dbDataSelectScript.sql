SELECT * FROM books;

SELECT * FROM books
WHERE id=5;

SELECT id FROM books
WHERE title='title_2';

SELECT * FROM books
WHERE title='title_2' AND author='author_2';

SELECT * FROM books
WHERE title='title_1' OR author='author_2';

SELECT * FROM books
ORDER BY id DESC;

SELECT * FROM books
ORDER BY id ASC;

SELECT * FROM books
ORDER BY description ASC;

SELECT * FROM books
ORDER BY description DESC;

SELECT * FROM books
WHERE title LIKE 't%'
LIMIT 2;

SELECT * FROM books
WHERE title LIKE 't%'
LIMIT 3 OFFSET 3;