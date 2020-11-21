package estore.core.responses;

import estore.domain.Product;

import java.util.List;

public class AddNewProductResponse extends CoreResponse {

    private Product product;
    private boolean SuccessfullyAdded;

    public AddNewProductResponse(List<CoreError> errors) {
        super(errors);
        this.SuccessfullyAdded = false;
    }

    public AddNewProductResponse(Product product) {
        this.product = product;
        this.SuccessfullyAdded = false;
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
