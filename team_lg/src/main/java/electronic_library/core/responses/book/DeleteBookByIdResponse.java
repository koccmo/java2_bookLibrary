package electronic_library.core.responses.book;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

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
