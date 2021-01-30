package estore.core.responses;

import estore.core.validation.CoreError;
import estore.core.domain.ProductCategory;

import java.util.List;

public class AddProductCategoryResponse extends CoreResponse {

    private ProductCategory category;
    private boolean successfullyAdded;

    public AddProductCategoryResponse(List<CoreError> errors) {
        super(errors);
        this.successfullyAdded = false;
    }

    public AddProductCategoryResponse(ProductCategory category) {
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
