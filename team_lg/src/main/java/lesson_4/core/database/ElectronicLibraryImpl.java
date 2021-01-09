package lesson_4.core.database;

import lesson_4.core.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ElectronicLibraryImpl implements ElectronicLibrary {

    private final List<Book> bookList = new ArrayList<>();
    private Long bookId = 1L;

    @Override
    public void saveBook(Book book) {
        book.setId(bookId);
        bookId++;
        bookList.add(book);
    }

    @Override
    public boolean deleteBook(Book book) {
        for (Book books : bookList) {
            if ((books.getBookTitle().equals(book.getBookTitle())) &&
                    (books.getBookAuthor().equals(book.getBookAuthor()))) {
                bookList.remove(book);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteBookById(Long id) {
        for (Book book:bookList) {
            if (book.getId().equals(id)){
                bookList.remove(book);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteBookByTitle(String bookTitle) {
        return bookList.removeIf(books -> (books.getBookTitle().equals(bookTitle)));
    }

    @Override
    public boolean deleteBookByAuthor(String bookAuthor) {
        return bookList.removeIf(books -> (books.getBookAuthor().equals(bookAuthor)));
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookList.stream()
                .filter(books -> books.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Book> findBookByTitle(String bookTitle) {
        return bookList.stream()
                .filter(books -> books.getBookTitle().equalsIgnoreCase(bookTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBookByAuthor(String bookAuthor) {
        return bookList.stream()
                .filter(books -> books.getBookAuthor().equalsIgnoreCase(bookAuthor))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByTitleAndAuthor(String bookTitle, String bookAuthor) {
        return bookList.stream()
                .filter(books -> books.getBookAuthor().equals(bookAuthor))
                .filter(book -> book.getBookTitle().equals(bookTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getElectronicLibrary() {
        return bookList;
    }
}
