package lesson_12.core.responses.book;

import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class DeleteBookByIdResponse extends CoreResponse {

    private boolean bookRemoved;

    public DeleteBookByIdResponse(boolean bookRemoved) {
        this.bookRemoved = bookRemoved;
    }
    public DeleteBookByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isBookRemoved() {
        return bookRemoved;
    }
}
