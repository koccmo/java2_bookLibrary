package internet_store.application.core.requests.product;

public class FindByIdRequest {

    private String productId;

    public FindByIdRequest() {
    }

    public FindByIdRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
