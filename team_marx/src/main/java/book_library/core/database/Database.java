package book_library.core.database;

import book_library.Book;

import java.util.List;

public interface Database {

    void save(Book book);

    boolean deleteById(Long id);

    boolean hasTheSameBookInDatabase (Book bookToCompare);

    List<Book> getAllBooks();

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleAndAuthor(String title, String author);



}
