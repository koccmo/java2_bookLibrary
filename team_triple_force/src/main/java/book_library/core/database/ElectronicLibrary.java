package book_library.core.database;

import book_library.core.domain.Book;

import java.util.List;

public interface ElectronicLibrary {

    void saveBook(Book book);

    boolean deleteBook(Book book);

    void deleteBookById(Long BookId);

    void deleteBookByTitle(String bookTitle);

    void deleteBookByAuthor(String bookAuthor);

    List<Book> getElectronicLibrary();

}
