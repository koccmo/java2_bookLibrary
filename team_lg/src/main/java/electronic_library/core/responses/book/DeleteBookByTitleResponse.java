package electronic_library.core.responses.book;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

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
