SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


CREATE SCHEMA IF NOT EXISTS `java2` DEFAULT CHARACTER SET utf8 ;
USE `java2` ;

CREATE TABLE IF NOT EXISTS `books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `author` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


ALTER TABLE `books`
  ADD `page_count` INT;


ALTER TABLE `books`
  ADD `description` VARCHAR(1000);


CREATE TABLE IF NOT EXISTS `readers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(200) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


CREATE TABLE IF NOT EXISTS `reader_books` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `reader_id` BIGINT NOT NULL,
  `book_id` BIGINT NOT NULL,
  `book_out_date` DATETIME NOT NULL,
  `book_return_date` DATETIME,
  PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1002;


ALTER TABLE `reader_books`
ADD FOREIGN KEY (`book_id`) REFERENCES `books`(`id`);

ALTER TABLE `reader_books`
ADD FOREIGN KEY (`reader_id`) REFERENCES `readers`(`id`);


CREATE INDEX ix_reader_books_reader_id
ON reader_books (reader_id);

CREATE INDEX ix_reader_books_book_id
ON reader_books (book_id);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;