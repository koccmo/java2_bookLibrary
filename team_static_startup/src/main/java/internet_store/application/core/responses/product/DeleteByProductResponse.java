package internet_store.application.core.responses.product;

import internet_store.application.core.domain.Product;

import java.util.List;

public class DeleteByProductResponse extends CoreResponse {

    private Product deletedProduct;

    public DeleteByProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteByProductResponse(Product deletedProduct) {
        this.deletedProduct = deletedProduct;
    }

    public Product getDeletedProduct() {
        return deletedProduct;
    }

}
