package estore.core.requests;

public class AddProductCategoryRequest {

    private String productCategory;

    public AddProductCategoryRequest(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }
}
