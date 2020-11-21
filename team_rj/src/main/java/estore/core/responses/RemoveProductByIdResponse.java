package estore.core.responses;

import java.util.List;

public class RemoveProductByIdResponse extends CoreResponse {

    private int productsRemoved;

    public RemoveProductByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveProductByIdResponse(int productsRemoved) {
        this.productsRemoved = productsRemoved;
    }

    public int getProductsRemoved() {
        return productsRemoved;
    }

}
