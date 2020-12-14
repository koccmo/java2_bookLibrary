package estore.core.requests;

public class SearchProductByNameRequest {

    private String productName;
    private Ordering ordering;
    private Paging paging;

    public SearchProductByNameRequest(String productName) {
        this.productName = productName;
    }

    public SearchProductByNameRequest(String productName, Ordering ordering) {
        this.productName = productName;
        this.ordering = ordering;
    }

    public SearchProductByNameRequest(String productName, Paging paging) {
        this.productName = productName;
        this.paging = paging;
    }

    public SearchProductByNameRequest(String productName, Ordering ordering, Paging paging) {
        this.productName = productName;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getProductName() {
        return productName;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
