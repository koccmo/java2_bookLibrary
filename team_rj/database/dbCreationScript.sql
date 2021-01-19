SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `eStore` DEFAULT CHARACTER SET utf8 ;
USE `eStore` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE TABLE IF NOT EXISTS productCategory (
	id bigint PRIMARY KEY AUTO_INCREMENT,
    category varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
	id bigint PRIMARY KEY AUTO_INCREMENT,
    prodName varchar(50) NOT NULL,
    prodDescription varchar(500) DEFAULT NULL,
    category_Id bigint NOT NULL,
    quantity int NOT NULL,
    price double(6, 2) DEFAULT NULL,
    dateOnStock DATETIME NOT NULL,
    FOREIGN KEY (category_Id) references productCategory(id)
);

CREATE TABLE IF NOT EXISTS clients (
	id bigint PRIMARY KEY AUTO_INCREMENT,
    firstName varchar(30) NOT NULL,
    lastName varchar(30) NOT NULL,
    phone varchar(15),
    address varchar(100)
);

CREATE TABLE IF NOT EXISTS orders (
	id bigint PRIMARY KEY AUTO_INCREMENT,
    client_id bigint NOT NULL,
    orderDate DATETIME NOT NULL,
    orderStatus varchar(30) NOT NULL,
    closeDate DATETIME,
    FOREIGN KEY (client_id) references clients(id)
);

CREATE TABLE IF NOT EXISTS order_details (
    order_id bigint NOT NULL,
    product_id bigint NOT NULL,
    amount int NOT NULL,
    pricePerUnit double(6, 2) NOT NULL,
    FOREIGN KEY (product_id) references products(id),
    FOREIGN KEY (order_id) references orders(id)
);

ALTER TABLE products
ADD COLUMN image LONGBLOB;