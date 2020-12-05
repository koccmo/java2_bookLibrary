package internet_store.core.responses;

import internet_store.core.domain.Product;

import java.util.List;


public class DeleteProductResponse extends CoreResponse {

    private Product deleteProduct;

    public DeleteProductResponse(List<CoreError> errors) { super(errors);}

    public DeleteProductResponse(Product deleteProduct) { this.deleteProduct = deleteProduct; }

    public Product getDeleteProduct() { return deleteProduct; }
}
