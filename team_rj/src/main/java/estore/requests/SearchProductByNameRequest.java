package estore.requests;

public class SearchProductByNameRequest {

    private String productName;

    public SearchProductByNameRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

}
