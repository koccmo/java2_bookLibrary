USE e_store;

INSERT INTO products(name, description, price)
VALUES ("mouse", "wireless", 10.0);

INSERT INTO products(name, description, price)
VALUES ("keyboard", "wired", 20.0);

INSERT INTO products(name, description, price)
VALUES ("monitor", "curved", 200.0);

INSERT INTO products(id, name, description, price)
VALUES (1004, "laptop", "MacBook Air", 1000.0);

INSERT INTO products(id, name, description, price)
VALUES (1005, "USB hub", "type-C", 10.0);


INSERT INTO users(first_name, last_name, email, profile_created)
VALUES ("Aname", "Asurname", "Auser@gmail.com", "2020-01-01");

INSERT INTO users(first_name, last_name, email, profile_created)
VALUES ("Bname", "Bsurname", "Buser@inbox.com", "2020-02-02");

INSERT INTO users(id, first_name, last_name, email, profile_created)
VALUES (2003, "Cname", "Csurname", "Cuser@gmail.com", "2020-03-03");

INSERT INTO users(first_name, last_name, email, profile_created)
VALUES ("Dname", "Dsurname", "Duser@inbox.lv", "2020-04-04");


INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3001, 2002, 1001, '2021-01-13 20:17:00');

INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3002, 2003, 1004, '2021-01-13 23:20:00');

INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3003, 2001, 1001, '2021-01-14 09:05:00');

INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3004, 2002, 1002, '2021-01-01 10:10:00');

INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3005, 2003, 1003, '2021-01-01 11:15:00');

INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3006, 2001, 1004, '2021-01-01 12:20:00');

INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3007, 2002, 1005, '2021-01-01 13:25:00');

INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3008, 2003, 1001, '2021-01-01 14:35:00');

INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3009, 2001, 1002, '2021-01-01 15:45:00');

INSERT INTO deals(id, user_id, product_id, deal_date)
VALUES (3010, 2002, 1003, '2021-01-01 16:55:00');