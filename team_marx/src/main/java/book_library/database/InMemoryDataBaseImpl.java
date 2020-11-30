package book_library.database;

import book_library.Book;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataBaseImpl implements Database {

    private Long nexId = 1L;
    private List<Book> books = new ArrayList<>();

    @Override
    public void save(Book book) {
        book.setId(nexId);
        nexId++;
        books.add(book);
    }

    @Override
    public void deleteById(Long id) {
        books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .ifPresent(book -> books.remove(book));

    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }
}
