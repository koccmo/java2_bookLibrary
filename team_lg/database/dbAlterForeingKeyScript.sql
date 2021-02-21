ALTER TABLE `reader_books`
ADD FOREIGN KEY (`book_id`) REFERENCES `books`(`id`);

ALTER TABLE `reader_books`
ADD FOREIGN KEY (`reader_id`) REFERENCES `readers`(`id`);