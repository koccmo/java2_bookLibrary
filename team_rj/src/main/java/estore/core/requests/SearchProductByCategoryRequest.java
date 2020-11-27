package estore.core.requests;

public class SearchProductByCategoryRequest {

    private String productCategory;
    private String orderBy;
    private String orderDirection;

    public SearchProductByCategoryRequest(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }

}
