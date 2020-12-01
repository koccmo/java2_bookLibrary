package internet_store.core.services.product;

import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.database.product.ProductDatabase;
import internet_store.core.domain.Product;

import java.util.List;

public class DeleteByIdService {

    private final ProductDatabase productDatabase;
    private final DeleteProductRequestValidator deleteProductRequestValidator;

    public DeleteByIdService(ProductDatabase productDatabase, DeleteProductRequestValidator deleteProductRequestValidator) {
        this.productDatabase = productDatabase;
        this.deleteProductRequestValidator = deleteProductRequestValidator;
    }

    public DeleteProductResponse execute(DeleteProductRequest deleteProductRequest) {

        List<CoreError> errors = deleteProductRequestValidator.validate(deleteProductRequest);

        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }

        if (productDatabase.containsId(deleteProductRequest.getId())){
            for (int i = 0; i < productDatabase.getProducts().size(); i++) {
                if (getCurrentProduct(i).getId() == deleteProductRequest.getId()) {
                    productDatabase.deleteById(deleteProductRequest.getId());
                    return new DeleteProductResponse(deleteProductRequest.getId());
                }
            }
        }

        errors.add(new CoreError("database", "database doesn't contain product with id "
                + deleteProductRequest.getId()));
        return new DeleteProductResponse(errors);
    }

    private Product getCurrentProduct (int index){
        return productDatabase.getProducts().get(index);
    }
}
