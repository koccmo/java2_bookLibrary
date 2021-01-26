package estore.core.requests;

public class SearchProductByIdRequest {

    private String productId;

    public SearchProductByIdRequest(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

}
