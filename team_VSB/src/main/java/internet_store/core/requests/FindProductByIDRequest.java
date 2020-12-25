package internet_store.core.requests;

public class FindProductByIDRequest {

    private String productId;

    public FindProductByIDRequest(String productId) { this.productId = productId; }

    public String getProductId() { return productId; }
}
