package lv.estore.app.core.responses;

import lv.estore.app.core.errors.CoreError;

import java.util.List;

public class AddResponse extends CoreResponse {

    private boolean productAdded;

    public AddResponse(boolean productAdded) {
        this.productAdded = productAdded;
    }

    public AddResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isProductAdded(){
        return productAdded;
    }
}
