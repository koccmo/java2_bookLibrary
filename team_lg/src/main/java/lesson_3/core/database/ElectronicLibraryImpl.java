package lesson_3.core.database;

import lesson_3.core.domain.Book;

import java.util.ArrayList;
import java.util.List;

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
    public void deleteBookById(Long id) {
        bookList.removeIf(books -> (books.getId().equals(id)));
    }

    @Override
    public void deleteBookByTitle(String bookTitle) {
        bookList.removeIf(books -> (books.getBookTitle().equals(bookTitle)));
    }

    @Override
    public void deleteBookByAuthor(String bookAuthor) {
        bookList.removeIf(books -> (books.getBookAuthor().equals(bookAuthor)));
    }

    @Override
    public List<Book> getElectronicLibrary() {
        return bookList;
    }
}
