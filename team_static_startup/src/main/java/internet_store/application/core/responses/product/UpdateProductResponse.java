package internet_store.application.core.responses.product;

import internet_store.application.core.domain.Product;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class UpdateProductResponse extends CoreResponse {

    private Product updatedProduct;

    public UpdateProductResponse(Product updatedProduct) {
        this.updatedProduct = updatedProduct;
    }

    public UpdateProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public Product getUpdatedProduct() {
        return updatedProduct;
    }

}
