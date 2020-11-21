package estore.core.responses;

import estore.core.validation.CoreError;
import estore.domain.Product;

import java.util.List;

public class AddNewProductResponse extends CoreResponse {

    private Product product;
    private boolean successfullyAdded;

    public AddNewProductResponse(List<CoreError> errors) {
        super(errors);
        this.successfullyAdded = false;
    }

    public AddNewProductResponse(Product product) {
        this.product = product;
        this.successfullyAdded = false;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isSuccessfullyAdded() {
        return successfullyAdded;
    }

    public void setSuccessfullyAdded(boolean successfullyAdded) {
        this.successfullyAdded = successfullyAdded;
    }
}
