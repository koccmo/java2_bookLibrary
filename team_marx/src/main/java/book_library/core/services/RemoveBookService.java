package book_library.core.services;

import book_library.core.database.Database;
import book_library.core.requests.RemoveBookRequest;
import book_library.core.responses.RemoveBookResponse;

public class RemoveBookService {

    private Database database;

    public RemoveBookService(Database database) {
        this.database = database;
    }

    public RemoveBookResponse execute (RemoveBookRequest request){

        boolean isBookRemoved = database.deleteById(request.getBookIdToRemove());
        return new RemoveBookResponse(isBookRemoved);
    }
}
