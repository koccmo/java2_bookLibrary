SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `e_store` DEFAULT CHARACTER SET utf8;
USE `e_store`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE TABLE IF NOT EXISTS `products` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(200) NOT NULL,
    `description` VARCHAR(1000) NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;

CREATE TABLE IF NOT EXISTS `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(200) NOT NULL,
    `last_name` VARCHAR(200) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `profile_created` DATETIME,
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 2001;

CREATE TABLE IF NOT EXISTS `deals` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    `status` VARCHAR(100) NOT NULL,
    `deal_date` DATETIME,
    PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 3001;

CREATE TABLE IF NOT EXISTS `passwords` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `password` VARCHAR(200) NOT NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 4001;

CREATE INDEX ix_deals_user_id
ON deals (user_id);

CREATE INDEX ix_deals_product_id
ON deals (product_id);

DROP INDEX x_deals_user_id
ON deals;

DROP INDEX ix_deals_product_id
ON deals;

SET SQL_SAFE_UPDATES = 0;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;