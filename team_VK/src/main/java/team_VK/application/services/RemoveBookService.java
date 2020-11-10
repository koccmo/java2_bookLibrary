package team_VK.application.services;

import team_VK.application.Book;
import team_VK.application.database.Database;

public class RemoveBookService {

    private final Database database ;
    public RemoveBookService(Database database) {
        this.database = database;
    }

    public void removeBook (String bookTitle, String bookAuthor) {
        Book book = new Book(bookTitle, bookAuthor);
        database.deleteBook(book);
    }



}
