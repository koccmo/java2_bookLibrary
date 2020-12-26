package book_library.core.responses;

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
