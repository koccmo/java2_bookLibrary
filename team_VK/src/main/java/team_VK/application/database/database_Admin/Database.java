package team_VK.application.database.database_Admin;

import team_VK.application.core.domain.Book;

import java.util.List;

public interface Database {

    void addBook(Book book);
    void deleteBook(Book book);
    List<Book> getListBooks();

}
