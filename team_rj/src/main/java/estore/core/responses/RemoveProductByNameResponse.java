package estore.core.responses;

import estore.core.validation.CoreError;

import java.util.List;

public class RemoveProductByNameResponse extends CoreResponse {

    private int productsRemoved;

    public RemoveProductByNameResponse(List<CoreError> errors) {
        super(errors);
        this.productsRemoved = -1;
    }

    public RemoveProductByNameResponse(int productsRemoved) {
        this.productsRemoved = productsRemoved;
    }

    public int getProductsRemoved() {
        return productsRemoved;
    }

}
