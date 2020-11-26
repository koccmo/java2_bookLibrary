package estore.core.requests;

public class RemoveProductByIdRequest {

    private String productId;

    public RemoveProductByIdRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

}
