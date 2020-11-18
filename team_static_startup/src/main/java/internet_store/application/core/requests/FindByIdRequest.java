package internet_store.application.core.requests;

public class FindByIdRequest {

    private Long productId;

    public FindByIdRequest(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

}
