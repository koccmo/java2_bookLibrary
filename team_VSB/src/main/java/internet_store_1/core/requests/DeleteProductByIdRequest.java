package internet_store_1.core.requests;

public class DeleteProductByIdRequest {

    Long productId;

    public DeleteProductByIdRequest(Long productId) { this.productId = productId; }

    public Long getProductId() { return productId; }
}
