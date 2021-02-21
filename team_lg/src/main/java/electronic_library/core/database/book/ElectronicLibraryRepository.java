package electronic_library.core.database.book;

import electronic_library.core.domain.Book;
import electronic_library.core.domain.Reader;

import java.util.List;
import java.util.Optional;

public interface ElectronicLibraryRepository {

    void saveBook(Book book);

    boolean deleteBook(Book book);

    boolean deleteBookById(Long id);

    boolean deleteBookByTitle(String bookTitle);

    boolean deleteBookByAuthor(String bookAuthor);

    Optional<Book> findBookById(Long id);

    List<Book> findBookByTitle(String bookTitle);

    List<Book> findBookByAuthor(String bookAuthor);

    List<Book> findByTitleAndAuthor(String bookTitle, String bookAuthor);

    List<Book> getElectronicLibrary();

}
