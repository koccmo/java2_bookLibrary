package book_library.services;

import book_library.Book;
import book_library.database.Database;

public class AddBookService {

    private Database database;

    public AddBookService(Database database) {
        this.database = database;
    }

    public void execute (String bookTitle, String bookAuthor){
        Book book = new Book(bookTitle,bookAuthor);
        database.save(book);
    }
}
