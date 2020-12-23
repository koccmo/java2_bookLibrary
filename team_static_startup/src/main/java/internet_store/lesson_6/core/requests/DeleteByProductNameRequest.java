package internet_store.lesson_6.core.requests;

public class DeleteByProductNameRequest {

    private String productName;

    public DeleteByProductNameRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
