SET SQL_SAFE_UPDATES = 0;

UPDATE books
SET description='all records changed';

SET SQL_SAFE_UPDATES = 1;

UPDATE books
SET title='title_changed', author='author_changed', page_count = 55, description='changed'
WHERE id=1;

UPDATE books
SET title='new title'
WHERE id=5;

UPDATE books
SET id=2, title='title_changed', author='author_changed', page_count=55, description='changed'
WHERE id=5;