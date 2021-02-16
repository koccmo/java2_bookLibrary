package lv.estore.app.core.responses.products;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class UpdateProductResponse extends CoreResponse {
    private boolean productUpdated;

    public UpdateProductResponse(boolean productUpdated) {
        this.productUpdated = productUpdated;
    }

    public UpdateProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isProductUpdated() {
        return productUpdated;
    }
}
