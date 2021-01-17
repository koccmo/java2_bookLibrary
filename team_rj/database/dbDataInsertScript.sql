SELECT * FROM eStore.clients;
SELECT * FROM eStore.order_details;
SELECT * FROM eStore.orders;
SELECT * FROM eStore.productCategory;
SELECT * FROM eStore.products;

INSERT INTO productCategory (category)
VALUES 	('Fruits'),
		('Vegetables'),
        ('Bakery'),
        ('Meat'),
        ('Fish'),
        ('Drinks'),
        ('Alcohol'),
        ('Dairy products'),
        ('Grocery'),
        ('Frozen food');

INSERT INTO clients (firstName, lastName, phone, address)
VALUES 	('Liga', 'Ligatne', '29334455', 'Riga'),
		('Olita', 'Ozola', '29445566', 'Ogre'),
		('Juris', 'Jurins', '29556677', 'Riga'),
		('Jana', 'Jansone', '29667788', 'Riga'),
		('Peteris', 'Petersons', '29778899', 'Jelgava');

INSERT INTO products (prodName, prodDescription, category_id, quantity, price, dateOnStock)
VALUES	('Milk', 'Svaigpiens Zemturu 0,75L', 8, 7, 0.87, sysdate()),
		('Butter', 'Sviests EXPORTA 82,5% 200g', 8, 12, 2.15, sysdate()),
		('Beer', 'Alus TĒRVETES SENČU gaišais 4,5% 500ml', 7, 5, 1.10, sysdate()),
		('Beer', 'Alus VALMIERMUIŽA 5,2% 500 ml', 7, 12, 1.83, sysdate()),
		('Beer', 'Alus BAUSKAS tumšais 5,5% 1L PET', 7, 4, 2.62, sysdate()),
		('Fish', 'Jūras asaris atdzesēts 1kg', 5, 23, 9.59, sysdate()),
		('Fish', 'Atlantijas makrele atkausēta', 5, 11, 4.30, sysdate()),
		('Vegetables', 'Tomāti plūmju', 2, 3, 1.62, sysdate()),
		('Vegetables', 'Gurķi garie LATVIJA gab.', 2, 28, 1.59, sysdate()),
		('Fruits', 'Fizāļi 100g', 1, 18, 0.93, sysdate()),
		('Fruits', 'Vinogas tumšās bez kauliņiem 500g', 1, 12, 2.73, sysdate());

INSERT INTO orders (client_id, orderDate, orderStatus, closeDate)
VALUES 	(1, '2021-01-16 15:45:30', 'EXECUTED', '2021-01-16 19:27:30');

INSERT INTO orders (client_id, orderDate, orderStatus)
VALUES 	(2, '2021-01-17 10:31:30', 'ACTIVE');

INSERT INTO orders (client_id, orderDate, orderStatus, closeDate)
VALUES 	(5, '2021-01-10 8:12:21', 'EXECUTED', '2021-01-11 13:12:03');

INSERT INTO order_details (order_id, product_id, amount, pricePerUnit)
VALUES 	(1, 6, 1, 9.59),
		(1, 3, 3, 1.10),
		(2, 9, 1, 1.59),
		(2, 10, 2, 0.93),
		(2, 11, 1, 2.73),
		(2, 1, 1, 0.87),
		(2, 5, 3, 2.62),
		(3, 4, 10, 1.83),
		(3, 3, 10, 1.10),
		(3, 11, 5, 2.73);