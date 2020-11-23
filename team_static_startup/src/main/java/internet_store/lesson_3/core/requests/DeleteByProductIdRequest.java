package internet_store.lesson_3.core.requests;

public class DeleteByProductIdRequest {

    private String productId;

    public DeleteByProductIdRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
