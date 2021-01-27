package team_VK.application.database;

import team_VK.application.core.domain.Book;

import java.util.ArrayList;
import java.util.List;
//@Component
public class DatabaseInMemory implements Database {

    private long idCounter = 1L;
    List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        book.setID(idCounter);
        idCounter++;
        books.add(book);
    }

    @Override
    public boolean deleteBook(Book book) {
        books.remove(book);
        return true;
    }

    @Override
    public List<Book> getListBooks() {
        return null;
    }
}
