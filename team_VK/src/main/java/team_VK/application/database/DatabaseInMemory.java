package team_VK.application.database;

import team_VK.application.core.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInMemory implements Database {

    private long idCounter = 1L;
    List<Book> books = new ArrayList<>();

    public DatabaseInMemory() {
    }

    public DatabaseInMemory(List<Book> books) {
        this.books = books;
    }

    @Override
    public void addBook(Book book) {
        book.setID(idCounter);
        idCounter++;
        books.add(book);
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }

    @Override
    public List<Book> getListBooks() {
        return books;
    }
}
