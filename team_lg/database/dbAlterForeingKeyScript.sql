ALTER TABLE `reader_books`
ADD FOREIGN KEY (`book_id`) REFERENCES `book`(`id`);

ALTER TABLE `reader_books`
ADD FOREIGN KEY (`reader_id`) REFERENCES `reader`(`id`);