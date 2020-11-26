package internet_store.core.service.delete_from_cart;

import internet_store.ProductListApplication;
import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.delete_product_from_cart.DeleteProductFromCartRequest;
import internet_store.core.response.delete_from_cart.DeleteProductFromCartResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.cart_database.InnerCartDatabase;

import java.util.List;

public class DeleteProductFromCartService {
    private final InnerCartDatabase cartDatabase;

    public DeleteProductFromCartService(InnerCartDatabase cartDatabase) {
        this.cartDatabase = cartDatabase;
    }

    public DeleteProductFromCartResponse execute(DeleteProductFromCartRequest deleteProductFromCartRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>
                (deleteProductFromCartRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (isIdExist(deleteProductFromCartRequest.getId())) {
            Product deletedProduct = findProductById(deleteProductFromCartRequest.getId());
            ProductListApplication.cartDatabase.deleteProductFromCart(deletedProduct);
        } else {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new DeleteProductFromCartResponse(deleteProductFromCartRequest.getId());
        }
        return new DeleteProductFromCartResponse(errors);
    }

    private boolean isIdExist(long id) {
        return cartDatabase.isIdExist(id);
    }

    private Product findProductById(long id) {
        return cartDatabase.findById(id);
    }
}