package internet_store.lesson_6.core.requests;

public class DeleteByProductIdRequest {

    private Long productId;

    public DeleteByProductIdRequest(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
