package internet_store.application.core.requests;

public class DeleteByProductIdRequest {

    Long productId;

    public DeleteByProductIdRequest(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
