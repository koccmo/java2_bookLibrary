package estore.core.responses;

import estore.core.validation.CoreError;

import java.util.List;

public class RemoveProductByIdResponse extends CoreResponse {

    private int productsRemoved;

    public RemoveProductByIdResponse(List<CoreError> errors) {
        super(errors);
        this.productsRemoved = -1;
    }

    public RemoveProductByIdResponse(int productsRemoved) {
        this.productsRemoved = productsRemoved;
    }

    public int getProductsRemoved() {
        return productsRemoved;
    }

}
