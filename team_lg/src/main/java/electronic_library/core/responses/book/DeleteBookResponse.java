package electronic_library.core.responses.book;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

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
