package internet_store.application.core.responses;

import java.util.List;

public class DeleteByProductIdResponse extends CoreResponse {

    private boolean bookRemoved;

    public DeleteByProductIdResponse(boolean bookRemoved) {
        this.bookRemoved = bookRemoved;
    }

    public DeleteByProductIdResponse(List<CoreError> errors) {
        super(errors);
    }
    public boolean isBookRemoved() {
        return bookRemoved;
    }

}
