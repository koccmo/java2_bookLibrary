package internet_store.application.core.requests.product;

public class DeleteByProductIdRequest {

    private Long productId;

    public DeleteByProductIdRequest(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
