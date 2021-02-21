SELECT * FROM products;

SELECT * FROM users;

SELECT * FROM deals;


SELECT name, price FROM products
WHERE id = 1001;

SELECT first_name FROM users
WHERE id = 2002;

SELECT user_id FROM deals
WHERE id = 3001;


SELECT * FROM products
WHERE price = 10.00;

SELECT * FROM users
WHERE first_name = 'Bname';

SELECT * FROM deals
WHERE user_id = 2002;


SELECT * FROM products
WHERE price >= 20.00 AND price < 300;

SELECT * FROM users
WHERE first_name = 'Bname' OR email LIKE '%.lv';

SELECT * FROM deals
WHERE user_id = 2002 OR product_id = 1004;


SELECT * FROM products
WHERE price >= 10 AND price <= 20 ORDER BY price ASC;

SELECT * FROM users
WHERE first_name LIKE '%name' ORDER BY last_name DESC;

SELECT * FROM deals
ORDER BY id DESC;


SELECT * FROM products
ORDER BY price LIMIT 3 OFFSET 2;

SELECT * FROM users
ORDER BY last_name DESC LIMIT 2 OFFSET 1;

SELECT * FROM deals
LIMIT 1 OFFSET 1;