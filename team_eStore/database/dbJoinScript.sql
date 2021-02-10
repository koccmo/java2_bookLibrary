SELECT product_id, customers.first_name, customers.last_name, shopping_cart.purchase_date, shopping_cart.product_id
FROM shopping_cart
INNER JOIN customers ON shopping_cart.customer_id=customers.id;

SELECT * FROM `products` JOIN `shopping_cart`;

SELECT  customers.first_name, customers.last_name, shopping_cart.product_id
FROM shopping_cart
RIGHT JOIN customers ON shopping_cart.customer_id=customers.id;

SELECT  customers.first_name, customers.last_name, shopping_cart.product_id
FROM shopping_cart
LEFT JOIN customers ON shopping_cart.customer_id=customers.id;

SELECT  products.id, products.name, products.price, customers.first_name, customers.last_name, purchase_date
FROM shopping_cart
INNER JOIN customers ON shopping_cart.customer_id=customers.id
INNER JOIN products ON shopping_cart.product_id=products.id;

SELECT  products.id, products.name, products.price, customers.first_name, customers.last_name, purchase_date
FROM shopping_cart
INNER JOIN customers ON shopping_cart.customer_id=customers.id
INNER JOIN products ON shopping_cart.product_id=products.id
WHERE products.name = 'mouse';

SELECT  products.name, shopping_cart.id, COUNT(shopping_cart.product_id) AS number_of_orders
FROM shopping_cart
LEFT JOIN products ON shopping_cart.product_id=products.id
group by products.name
HAVING COUNT(shopping_cart.product_id) > 0
ORDER BY COUNT(shopping_cart.product_id) DESC;