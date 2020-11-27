package internet_store.lesson_3.core.requests;

public class FindByProductNameRequest {

    String productName;

    public FindByProductNameRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

}
