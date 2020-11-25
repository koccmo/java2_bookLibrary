package internet_store_1.application.core.requests;

public class FindByIdRequest {

    private String productId;

    public FindByIdRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

}
