package lesson_4.core.responses;

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
