package estore.responses;

import estore.domain.Product;

public class AddNewProductResponse {

    private Product product;
    private boolean SuccessfullyAdded;

    public AddNewProductResponse(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isSuccessfullyAdded() {
        return SuccessfullyAdded;
    }

    public void setSuccessfullyAdded(boolean successfullyAdded) {
        SuccessfullyAdded = successfullyAdded;
    }
}
