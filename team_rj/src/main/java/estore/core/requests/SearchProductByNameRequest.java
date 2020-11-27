package estore.core.requests;

public class SearchProductByNameRequest {

    private String productName;
    private String orderBy;
    private String orderDirection;

    public SearchProductByNameRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

}
