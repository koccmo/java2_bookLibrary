package estore.core.requests;

public class SearchProductByNameRequest {

    private String productName;
    private Ordering ordering;
    private String orderBy;
    private String orderDirection;

    public SearchProductByNameRequest(String productName) {
        this.productName = productName;
    }

    public SearchProductByNameRequest(String productName, Ordering ordering) {
        this.productName = productName;
        this.ordering = ordering;
    }

    public String getProductName() {
        return productName;
    }

    public Ordering getOrdering() {
        return ordering;
    }
}
