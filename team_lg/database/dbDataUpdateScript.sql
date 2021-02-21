SET SQL_SAFE_UPDATES = 0;

UPDATE books
SET description='description';

SET SQL_SAFE_UPDATES = 1;

UPDATE books
SET bookTitle='new title ', bookAuthor='new author', page_count = 80, description='new description'
WHERE id=2;

UPDATE books
SET bookTitle='new title'
WHERE id=6;

UPDATE books
SET id=3, bookTitle='new title', bookAuthor='new author', page_count=15, description='new description'
WHERE id=3;
