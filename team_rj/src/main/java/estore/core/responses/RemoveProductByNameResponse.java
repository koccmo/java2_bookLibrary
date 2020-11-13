package estore.core.responses;

public class RemoveProductByNameResponse {

    private int productsRemoved;

    public RemoveProductByNameResponse(int productsRemoved) {
        this.productsRemoved = productsRemoved;
    }

    public int getProductsRemoved() {
        return productsRemoved;
    }

}
