package internet_store_1.application.core.requests;

public class DeleteByProductIdRequest {

    private String productId;

    public DeleteByProductIdRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
