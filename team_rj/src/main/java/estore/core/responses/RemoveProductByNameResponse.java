package estore.core.responses;

import java.util.List;

public class RemoveProductByNameResponse extends CoreResponse {

    private int productsRemoved;

    public RemoveProductByNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveProductByNameResponse(int productsRemoved) {
        this.productsRemoved = productsRemoved;
    }

    public int getProductsRemoved() {
        return productsRemoved;
    }

}
