package estore.requests;

public class RemoveProductByIdRequest {

    private long productId;

    public RemoveProductByIdRequest(long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

}
