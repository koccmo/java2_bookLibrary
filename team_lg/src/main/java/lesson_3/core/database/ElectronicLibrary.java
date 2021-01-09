package lesson_3.core.database;

import lesson_3.core.domain.Book;

import java.util.List;

public interface ElectronicLibrary {

    void saveBook(Book book);

    boolean deleteBook(Book book);

    void deleteBookById(Long BookId);

    void deleteBookByTitle(String bookTitle);

    void deleteBookByAuthor(String bookAuthor);

    List<Book> getElectronicLibrary();

}
