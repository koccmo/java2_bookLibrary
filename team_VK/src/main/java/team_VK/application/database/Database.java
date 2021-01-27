package team_VK.application.database;

import team_VK.application.core.domain.Book;

import java.util.List;

public interface Database {

    void addBook(Book book);
    boolean deleteBook(Book book);
    List<Book> getListBooks();

}
