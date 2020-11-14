package internet_store.application.core.responses;

import java.util.List;

public class DeleteByProductNameResponse extends CoreResponse {

    private boolean bookRemoved;

    public DeleteByProductNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteByProductNameResponse(boolean bookRemoved) {
        this.bookRemoved = bookRemoved;
    }

    public boolean isBookRemoved() {
        return bookRemoved;
    }
}
