package team_triple_force.application;

import java.util.ArrayList;
import java.util.List;

public class ElectronicLibraryImpl implements ElectronicLibrary {

    private final List<Book> bookList = new ArrayList<>();
    private Long bookId;

    @Override
    public Long saveBook(Book book) {
        book.setId(bookId);
        bookList.add(book);
        return ++bookId;
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
    public boolean deleteBookById(Book BookId) {
        for (Book book : bookList) {
            if (book.getId().equals(bookId)) {
                bookList.remove(book);
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteBookByTitle(String bookTitle) {
        bookList.removeIf(books -> (books.getBookTitle().equals(bookTitle)));
    }
}
