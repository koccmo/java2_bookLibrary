package estore.core.requests;

public class SearchProductByCategoryRequest {

    private String productCategory;
    private Ordering ordering;
    private Paging paging;

    public SearchProductByCategoryRequest(String productCategory) {
        this.productCategory = productCategory;
    }

    public SearchProductByCategoryRequest(String productCategory, Ordering ordering) {
        this.productCategory = productCategory;
        this.ordering = ordering;
    }

    public SearchProductByCategoryRequest(String productCategory, Paging paging) {
        this.productCategory = productCategory;
        this.paging = paging;
    }

    public SearchProductByCategoryRequest(String productCategory, Ordering ordering, Paging paging) {
        this.productCategory = productCategory;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
