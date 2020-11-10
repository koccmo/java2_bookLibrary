package team_VK.application.services;

import team_VK.application.Book;
import team_VK.application.database.Database;

public class AddBookService {

    private final Database database;

    public AddBookService(Database database) {
        this.database = database;
    }

    public void addBook(String bookTitle, String bookAuthor){
        Book book = new Book(bookTitle, bookAuthor);
        database.addBook(book);

    }
}
