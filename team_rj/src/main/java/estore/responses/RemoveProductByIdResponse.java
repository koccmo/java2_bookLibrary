package estore.responses;

public class RemoveProductByIdResponse {

    private int productsRemoved;

    public RemoveProductByIdResponse(int productsRemoved) {
        this.productsRemoved = productsRemoved;
    }

    public int getProductsRemoved() {
        return productsRemoved;
    }

}
