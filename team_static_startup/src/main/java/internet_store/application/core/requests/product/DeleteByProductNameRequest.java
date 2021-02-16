package internet_store.application.core.requests.product;

public class DeleteByProductNameRequest {

    private String productName;

    public DeleteByProductNameRequest() {
    }

    public DeleteByProductNameRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
