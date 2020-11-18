package internet_store.application.core.requests;

public class FindByProductNameRequest {

    String productName;

    public FindByProductNameRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

}
