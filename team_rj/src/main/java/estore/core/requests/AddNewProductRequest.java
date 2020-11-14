package estore.core.requests;

public class AddNewProductRequest {

    private String productName;
    private String productDescription;

    public AddNewProductRequest(String productName, String productDescription) {
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
