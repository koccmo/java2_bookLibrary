package internet_store.application.core.responses.product;

import internet_store.application.core.domain.Product;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class DeleteProductResponse extends CoreResponse {

    private Product product;

    public DeleteProductResponse(Product product) {
        this.product = product;
    }

    public DeleteProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public Product getProduct() {
        return product;
    }

}
