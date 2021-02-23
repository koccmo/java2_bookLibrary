package book_library.core.responses.Book;

import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class RemoveBookResponse extends CoreResponse {

    private boolean bookRemoved;

    public RemoveBookResponse(boolean bookRemoved) {
        this.bookRemoved = bookRemoved;
    }

    public boolean isBookRemoved() {
        return bookRemoved;
    }

    public RemoveBookResponse(List<CoreError> errors) {
        super(errors);
    }
}
