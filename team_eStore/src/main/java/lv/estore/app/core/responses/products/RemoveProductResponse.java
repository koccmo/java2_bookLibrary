package lv.estore.app.core.responses.products;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class RemoveProductResponse extends CoreResponse {

    private boolean productRemoved;

    public RemoveProductResponse(boolean productRemoved) {
        this.productRemoved = productRemoved;
    }

    public RemoveProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isProductRemoved() {
        return productRemoved;
    }
}
