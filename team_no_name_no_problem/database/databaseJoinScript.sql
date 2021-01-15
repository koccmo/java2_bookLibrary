SELECT customer_id FROM customer_order WHERE price > 400;

SELECT price FROM customer_order WHERE customer_id = 'Jaroslav' ORDER BY ASC;

SELECT * FROM customer_order ORDER BY price DESC;

SELECT * FROM customer_order WHERE product_id = 'printer';