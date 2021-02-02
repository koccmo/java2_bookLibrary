package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.product.DeleteProductRequest;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.validate.NumberValidator;
import internet_store.database.product_database.ProductDatabaseImpl;
import internet_store.persistence.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteProductService {

    public DeleteProductResponse execute(DeleteProductRequest request) {
        NumberValidator<?> numberValidator = new NumberValidator<>(request.getId());

        List<CoreError> errors = numberValidator.validate();

        Object databases = request.getProductDatabase();

        if (databases instanceof ProductRepository) {
            ((ProductRepository) databases).delete(request.getProduct());
            return new DeleteProductResponse(new ArrayList<>());
        }

        if (databases instanceof ProductDatabaseImpl) {
            if (((ProductDatabaseImpl) databases).isIdExist(request.getId())) {
                Product deletedProduct = ((ProductDatabaseImpl) databases).findById(request.getId());
                ((ProductDatabaseImpl) databases).deleteProduct(deletedProduct);
            } else {
                errors.add(new CoreError("Id error ", "wrong ID"));
            }
        }

        if (errors.isEmpty()) {
            return new DeleteProductResponse(request.getId());
        }
        return new DeleteProductResponse(errors);
    }
}