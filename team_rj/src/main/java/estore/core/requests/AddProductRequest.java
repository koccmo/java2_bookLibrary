package estore.core.requests;

public class AddProductRequest {

    private String productName;
    private String productDescription;
    private String productCategory;

    public AddProductRequest(String productName, String productDescription, String productCategory) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }
}
