package internet_store.application.core.responses;

import java.util.List;

public class DeleteByProductIdResponse extends CoreResponse {

    private boolean productRemoved;

    public DeleteByProductIdResponse(boolean productRemoved) {
        this.productRemoved = productRemoved;
    }

    public DeleteByProductIdResponse(List<CoreError> errors) {
        super(errors);
    }
    public boolean isBookRemoved() {
        return productRemoved;
    }

}
