package lesson_12.core.responses.book;

import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class DeleteBookByTitleResponse extends CoreResponse {

    private boolean bookRemoved;

    public DeleteBookByTitleResponse(boolean bookRemoved) {
        this.bookRemoved = bookRemoved;
    }

    public DeleteBookByTitleResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isBookRemoved() {
        return bookRemoved;
    }
}
