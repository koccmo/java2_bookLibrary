package bookLibrary.core.dataBase;

import bookLibrary.Book;
import bookLibrary.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DIComponent
public class InMemoryDatabaseImpl implements DataBase{
    private Long idNumber = 1L;
    private List<Book> bookLibrary = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        book.setId(idNumber);
        bookLibrary.add(book);
        idNumber++;
    }

    @Override
    public void deleteBook(Long id) {
        Book bookToDelete = bookLibrary.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst().get();
        bookLibrary.remove(bookToDelete);
    }

    @Override
    public List<String> getAllBooksTitle() {
        return bookLibrary.stream()
                .map(book -> book.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookLibrary.stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public void finishWork() {
        System.exit(0);
    }

    @Override
    public boolean hasBookInLibrary(Book book) {
        return bookLibrary.contains(book);
    }

    @Override
    public boolean hasBookInLibraryCheckById(Long id) {
        boolean bookInLibrary = false;
        Optional<Book> bookToCheck = bookLibrary.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
        if (bookToCheck.isPresent()) {
            bookInLibrary = true;
        }
        return bookInLibrary;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookLibrary.stream()
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthorAndTitle(String author, String title) {
        Book searchingBook = new Book(author, title);
        return bookLibrary.stream()
                .filter(book -> book.equals(searchingBook))
                .collect(Collectors.toList());
    }
}
