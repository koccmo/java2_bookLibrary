package lesson_12.core.responses.book;

import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class DeleteBookResponse extends CoreResponse {

    private boolean bookRemoved;

    public DeleteBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteBookResponse(boolean bookRemoved) {
        this.bookRemoved = bookRemoved;
    }

    public boolean isBookRemoved() {
        return bookRemoved;
    }
}
