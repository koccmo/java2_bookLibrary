SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `internet_store` DEFAULT CHARACTER SET utf8 ;
USE `internet_store` ;

CREATE TABLE IF NOT EXISTS `customers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `address` VARCHAR(250) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(250) NOT NULL,
  `price` INTEGER(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `shopping_cart`  (
  `shopping_cart_id` BIGINT  NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT NOT NULL,
  `sum_total` INTEGER(100) NOT NULL,
  PRIMARY KEY (`shopping_cart_id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `shopping_cart_item` (
    `shopping_cart_item_id` BIGINT NOT NULL AUTO_INCREMENT,
    `shopping_cart_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    `quantity` INTEGER(100) NOT NULL,
    `price` INTEGER(10000) NOT NULL,
    PRIMARY KEY (`shopping_cart_item_id`),
    FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_cart`(`shopping_cart_id`),
    FOREIGN KEY (`product_id`) REFERENCES `products`(`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;


CREATE TABLE IF NOT EXISTS `order_item` (
    `order_item_id` BIGINT NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT NOT NULL,
    `shopping_cart_id` BIGINT NOT NULL,
    `itemQuantity` INTEGER (100) NOT NULL,
    PRIMARY KEY (`order_item_id`),
    FOREIGN KEY (`shopping_cart_id`) REFERENCES `shopping_cart`(`shopping_cart_id`),
    FOREIGN KEY (`product_id`) REFERENCES `products`(`id`)
)
ENGINE = innoDB
AUTO_INCREMENT = 1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;