package internet_store.core.requests;

public class DeleteProductByNameRequest {

    private String productName;

    public DeleteProductByNameRequest(String productName) { this.productName = productName; }

    public String getProductName() { return productName; }
}
