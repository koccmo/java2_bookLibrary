package internet_store.lesson_6.core.requests;

public class ChangeProductNameRequest {

private Long productId;
private String productNewName;

    public ChangeProductNameRequest(Long productId, String productNewName) {
        this.productId = productId;
        this.productNewName = productNewName;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductNewName() {
        return productNewName;
    }
}
