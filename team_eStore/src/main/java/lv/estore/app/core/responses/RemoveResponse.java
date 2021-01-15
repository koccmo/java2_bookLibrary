package lv.estore.app.core.responses;

import lv.estore.app.core.errors.CoreError;

import java.util.List;

public class RemoveResponse extends CoreResponse {

    private boolean productRemoved;

    public RemoveResponse(boolean productRemoved) {
        this.productRemoved = productRemoved;
    }

    public RemoveResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isProductRemoved() {
        return productRemoved;
    }
}
