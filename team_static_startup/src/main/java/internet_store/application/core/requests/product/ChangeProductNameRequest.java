package internet_store.application.core.requests.product;

public class ChangeProductNameRequest {

private Long productId;
private String productNewName;

    public ChangeProductNameRequest() {
    }

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

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductNewName(String productNewName) {
        this.productNewName = productNewName;
    }
}
