SELECT *
FROM customers;

SELECT *
FROM products;

SELECT *
FROM customer_order;

SELECT customers
FROM customers
WHERE id = 2;

SELECT products
FROM products
WHERE id = 3;

SELECT title
FROM customers

SELECT title
FROM products
WHERE price = 900

SELECT surname
FROM customers
WHERE surname LIKE %o%
ORDER BY surname DESC;

SELECT name, surname
FROM customer_order
JOIN customers
WHERE email = "ragu@inbox.lv"