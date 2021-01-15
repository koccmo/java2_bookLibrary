INSERT INTO customers (name, surname, address, email, phone)
VALUES ("Jaroslav", "Brutan", "Matisa 31", "brutaxa@inbox.lv", "28459123");

INSERT INTO customers (name, surname, address, email, phone)
VALUES ("Harry", "Potter", "Hogwartz", "potter@hogwartz.uk", "28767192");

INSERT INTO customers (name, surname,address, email, phone)
VALUES ("Doctor", "Evil", "earth", "evil@evil.com", "28749038");

INSERT INTO customers (id, name, surname, address, email, phone)
VALUES (4, "Anvar", "Intezar", "riga", "anvar4ik@inbox.lv", "2764832" );

INSERT INTO customers (id, name, surname, address, email, phone)
VALUES (5, "Valerija", "Ionova", "kreisais krasts", "ionova@gmail.com", "28947382");

INSERT INTO customers (id, name, surname, address, email, phone)
VALUES (6, "Aleksandr", "Aleksandrov", "valdemara 10", "ragu@inbox.lv", "23882716");

INSERT INTO customers (id, name, surname, address, email, phone)
VALUES (7, "Felix", "Ronis", "Lacplesa 10-2", "ronis@gmail.com", "22886382");

INSERT INTO customers (id, name, surname, address, email, phone)
VALUES ("Egor", "Hohlov", "Brivibas 29", "akulka@gmail.com", "24983923");

INSERT INTO products (title, description, price)
VALUES ("phone", "iphone11", 1200);

INSERT INTO products (title, description, price)
VALUES ("laptop", "Dell XPS", 900);

INSERT INTO product (id, title, description, price)
VALUES (3, "apple", "2kg og red apples", 4);

INSERT INTO product (id, title, description, price)
VALUES (4, "fridge", "A++", 800);

INSERT INTO product (id, title, description, price)
VALUES (5, "microwave", "philips", 120);

INSERT INTO product (id, title, description, price)
VALUES (6, "printer", "samsung", 200);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (1,2,900);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (2,3,4);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (4,1,1200);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (4,6,200);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (3,5,120);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (1,4,800);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (6,6,200);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (7,2,900);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (8,1,1200);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (5,6,200);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (5,5,120);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (2,4,800);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (3,6,200);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (6,1,1200);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (7,3,4);

INSERT INTO customer_order(customer_id, product_id, price)
VALUES (8,2,900);