package book_library.services;

import book_library.database.Database;

public class RemoveBookService {

    private Database database;

    public RemoveBookService(Database database) {
        this.database = database;
    }

    public void execute (Long bookId){
        database.deleteById(bookId);
    }
}
