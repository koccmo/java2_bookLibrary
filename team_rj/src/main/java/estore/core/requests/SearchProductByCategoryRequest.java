package estore.core.requests;

public class SearchProductByCategoryRequest {

    private String productCategory;
    private Ordering ordering;

    public SearchProductByCategoryRequest(String productCategory) {
        this.productCategory = productCategory;
    }

    public SearchProductByCategoryRequest(String productCategory, Ordering ordering) {
        this.productCategory = productCategory;
        this.ordering = ordering;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public Ordering getOrdering() {
        return ordering;
    }
}
