package internet_store.lesson_6.core.responses;

import java.util.List;

public class DeleteByProductIdResponse extends CoreResponse {

    private boolean productRemoved;

    public DeleteByProductIdResponse(boolean productRemoved) {
        this.productRemoved = productRemoved;
    }

    public DeleteByProductIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isProductRemoved() {
        return productRemoved;
    }

}
