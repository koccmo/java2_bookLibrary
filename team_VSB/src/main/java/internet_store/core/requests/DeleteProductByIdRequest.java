package internet_store.core.requests;

public class DeleteProductByIdRequest {

    Long productId;

    public DeleteProductByIdRequest(Long productId) { this.productId = productId; }

    public Long getProductId() { return productId; }
}
