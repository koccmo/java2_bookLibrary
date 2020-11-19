package internet_store.core.requests;

public class FindProductByNameRequest {

    private String productName;

    public FindProductByNameRequest(String productName) { this.productName = productName; }

    public String getProductName() { return productName; }
}
