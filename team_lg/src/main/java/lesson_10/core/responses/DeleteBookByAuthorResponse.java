package lesson_10.core.responses;

import java.util.List;

public class DeleteBookByAuthorResponse extends CoreResponse {

    private boolean bookRemoved;

    public DeleteBookByAuthorResponse(boolean bookRemoved) {
        this.bookRemoved = bookRemoved;
    }

    public DeleteBookByAuthorResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isBookRemoved() {
        return bookRemoved;
    }
}
