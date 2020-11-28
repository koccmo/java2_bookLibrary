package estore.core.requests;

public class AddNewProductCategoryRequest {

    private String productCategory;

    public AddNewProductCategoryRequest(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }
}
