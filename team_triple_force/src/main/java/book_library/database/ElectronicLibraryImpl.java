package book_library.database;

import book_library.core.domain.Book;

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
    public void deleteBookById(Long BookId) {
        bookList.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .ifPresent(bookList::remove);
    }

    @Override
    public void deleteBookByTitle(String bookTitle) {
        bookList.removeIf(books -> (books.getBookTitle().equals(bookTitle)));
    }

    @Override
    public List<Book> getElectronicLibrary() {
        return bookList;
    }
}
