package estore.core.responses;

import estore.core.validation.CoreError;
import estore.domain.ProductCategory;

import java.util.List;

public class AddNewProductCategoryResponse extends CoreResponse {

    private ProductCategory category;
    private boolean successfullyAdded;

    public AddNewProductCategoryResponse(List<CoreError> errors) {
        super(errors);
        this.successfullyAdded = false;
    }

    public AddNewProductCategoryResponse(ProductCategory category) {
        this.category = category;
        this.successfullyAdded = false;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public boolean isSuccessfullyAdded() {
        return successfullyAdded;
    }

    public void setSuccessfullyAdded(boolean successfullyAdded) {
        this.successfullyAdded = successfullyAdded;
    }
}
