package team_VK.application.database;

import team_VK.application.Book;

import java.util.List;

public interface Database {

    void addBook(Book book);
    void deleteBook(Book book);
    List<Book> getListBooks();

}
