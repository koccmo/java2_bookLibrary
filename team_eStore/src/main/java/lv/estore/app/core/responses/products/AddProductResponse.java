package lv.estore.app.core.responses.products;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class AddProductResponse extends CoreResponse {

    private boolean productAdded;

    public AddProductResponse(boolean productAdded) {
        this.productAdded = productAdded;
    }

    public AddProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isProductAdded(){
        return productAdded;
    }
}
