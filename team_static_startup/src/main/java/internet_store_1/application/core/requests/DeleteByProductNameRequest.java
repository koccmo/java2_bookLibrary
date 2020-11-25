package internet_store_1.application.core.requests;

public class DeleteByProductNameRequest {

    private String productName;

    public DeleteByProductNameRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
