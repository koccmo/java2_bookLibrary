package book_library.core.database;

import book_library.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public boolean deleteById(Long id) {
        boolean isBookDeleted = false;
        Optional<Book> bookToDeleteOPT = books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
        if (bookToDeleteOPT.isPresent()) {
            Book bookToRemove = bookToDeleteOPT.get();
            isBookDeleted = books.remove(bookToRemove);
        }
        return isBookDeleted;

    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }


    @Override
    public boolean hasTheSameBookInDatabase(Book bookToCompare) {

        return books.contains(bookToCompare);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());
    }
}
