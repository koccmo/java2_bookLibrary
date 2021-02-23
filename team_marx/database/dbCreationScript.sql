SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=1;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS marxLibrary DEFAULT CHARACTER SET utf8 ;
USE marxLibrary;

CREATE TABLE IF NOT EXISTS books (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  author VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

ALTER TABLE books
  ADD page_count INT;

ALTER TABLE books
  ADD description VARCHAR(1000);

CREATE TABLE IF NOT EXISTS readers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(200) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

ALTER TABLE readers
	ADD personal_code BIGINT(11) NOT NULL
    DEFAULT (11111111111);

CREATE TABLE IF NOT EXISTS reader_books (
  id BIGINT NOT NULL AUTO_INCREMENT,
  reader_id BIGINT NOT NULL,
  book_id BIGINT NOT NULL,
  book_out_date DATETIME NOT NULL,
  book_return_date DATETIME,
  PRIMARY KEY (id)
)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

ALTER TABLE reader_books
ADD FOREIGN KEY (book_id) REFERENCES books(id);

ALTER TABLE reader_books
ADD FOREIGN KEY (reader_id) REFERENCES readers(id);

SET SQL_SAFE_UPDATES = 0;

CREATE INDEX books_title_idx ON books (title);
CREATE INDEX books_author_idx ON books (author);

CREATE INDEX reader_books_book_out_date_idx ON reader_books (book_out_date);
CREATE INDEX reader_books_book_return_date_idx ON reader_books (book_return_date);

CREATE INDEX readers_first_name_idx ON readers (first_name);
CREATE INDEX readers_last_name_idx ON readers (last_name);

-- Lesson12_Task12_SubTask3
CREATE INDEX readers_first_name_last_name_personal_code_idx ON readers (first_name, last_name, personal_code);

CREATE INDEX reader_books_reader_id_idx ON reader_books (reader_id);
CREATE INDEX reader_books_book_id_idx ON reader_books (book_id);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;