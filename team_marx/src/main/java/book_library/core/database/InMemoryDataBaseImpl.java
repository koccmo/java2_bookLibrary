package book_library.core.database;

import book_library.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        if (bookToDeleteOPT.isPresent()){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryDataBaseImpl that = (InMemoryDataBaseImpl) o;
        return Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books);
    }

    private boolean hasTheSameBookInDatabase (Book bookToCompare){

        return books.stream().equals(bookToCompare);
    }
}
