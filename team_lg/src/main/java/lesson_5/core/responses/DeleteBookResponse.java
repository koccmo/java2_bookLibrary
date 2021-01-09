package lesson_5.core.responses;

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
