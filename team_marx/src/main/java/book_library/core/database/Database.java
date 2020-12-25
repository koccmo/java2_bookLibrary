package book_library.core.database;

import book_library.Book;

import java.util.List;

public interface Database {

    void save(Book book);

    boolean deleteById(Long id);

    List<Book> getAllBooks();

    boolean hasTheSameBookInDatabase (Book bookToCompare);
}
