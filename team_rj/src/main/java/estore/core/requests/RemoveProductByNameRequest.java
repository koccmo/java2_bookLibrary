package estore.core.requests;

public class RemoveProductByNameRequest {

    private String productName;

    public RemoveProductByNameRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

}
