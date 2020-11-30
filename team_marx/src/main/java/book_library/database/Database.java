package book_library.database;

import book_library.Book;

import java.util.List;

public interface Database {

    void save(Book book);

    void deleteById(Long id);

    List<Book> getAllBooks();
}
