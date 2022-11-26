package bookLibrary.core.response;

import java.util.List;

public class DeleteBookResponse extends CoreResponse{
    private boolean bookDeleted;

    public DeleteBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteBookResponse(boolean bookDeleted) {
        this.bookDeleted = bookDeleted;
    }

    public boolean isBookDeleted() {
        return bookDeleted;
    }

}
