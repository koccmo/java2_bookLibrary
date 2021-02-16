SELECT product_id, users.first_name, users.last_name, deals.deal_date, deals.product_id
FROM deals
INNER JOIN users ON deals.user_id=users.id;

SELECT * FROM `products` JOIN `deals`;

SELECT  users.first_name, users.last_name, deals.product_id
FROM deals
RIGHT JOIN users ON deals.user_id=users.id;

SELECT  users.first_name, users.last_name, deals.product_id
FROM deals
LEFT JOIN users ON deals.user_id=users.id;

SELECT  products.id, products.name, products.price, users.first_name, users.last_name, deal_date
FROM deals
INNER JOIN users ON deals.user_id=users.id
INNER JOIN products ON deals.product_id=products.id;

SELECT  products.id, products.name, products.price, users.first_name, users.last_name, deal_date
FROM deals
INNER JOIN users ON deals.user_id=users.id
INNER JOIN products ON deals.product_id=products.id
WHERE products.name = 'mouse';

SELECT  products.name, deals.id, COUNT(deals.product_id) AS number_of_orders
FROM deals
LEFT JOIN products ON deals.product_id=products.id
group by products.name
HAVING COUNT(deals.product_id) > 0
ORDER BY COUNT(deals.product_id) DESC;