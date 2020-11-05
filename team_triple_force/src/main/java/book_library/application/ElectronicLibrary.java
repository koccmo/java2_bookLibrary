package book_library.application;

import java.util.List;

public interface ElectronicLibrary {

    void saveBook(Book book);

    boolean deleteBook(Book book);

    void deleteBookById(Long BookId);

    void deleteBookByTitle(String bookTitle);

    List<Book> getElectronicLibrary();

}
