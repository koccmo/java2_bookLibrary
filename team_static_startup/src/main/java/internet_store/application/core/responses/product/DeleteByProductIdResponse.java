package internet_store.application.core.responses.product;

import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

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
