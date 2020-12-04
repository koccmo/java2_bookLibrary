package internet_store.lesson_5.core.responses;

import java.util.List;

public class DeleteByProductNameResponse extends CoreResponse {

    private boolean productRemoved;

    public DeleteByProductNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteByProductNameResponse(boolean productRemoved) {
        this.productRemoved = productRemoved;
    }

    public boolean isProductRemoved() {
        return productRemoved;
    }
}
