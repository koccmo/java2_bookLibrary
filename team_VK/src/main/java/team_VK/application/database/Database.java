package team_VK.application.database;

import team_VK.application.core.domain.Book;

public interface Database {

    void addBook(Book book);
    void deleteBook(Book book);
    void getListBooks();

}
