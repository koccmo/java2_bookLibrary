package internet_store.core.service.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Cart;
import internet_store.core.request.cart.DeleteProductFromCartRequest;
import internet_store.core.response.cart.DeleteProductFromCartResponse;
import internet_store.core.validate.NumberValidator;
import internet_store.database.cart_database.CartDatabaseImpl;
import internet_store.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DeleteProductFromCartService {
    @Autowired
    CartDatabaseImpl cartDatabase;
    @Autowired
    CartRepository cartRepository;

    public DeleteProductFromCartResponse execute(DeleteProductFromCartRequest request) {
        NumberValidator<?> numberValidator = new NumberValidator<>
                (request.getId());

        List<CoreError> errors = numberValidator.validate();
        Object databases = request.getClientDatabase();

        if (databases instanceof CartRepository) {
            Cart productInCart = cartRepository.findByTitle(request.getProductTitle());
            productInCart.setDeleted(true);
            cartRepository.save(productInCart);
            return new DeleteProductFromCartResponse(new ArrayList<>());
        }

        if (databases instanceof CartDatabaseImpl) {
            if (isIdExist(request.getId())) {
                Cart deletedProduct = findProductById(request.getId());
                deletedProduct.setDeleted(true);
                int listIndex = cartDatabase.findProductIndex(request.getId());
                cartDatabase.updateCart(listIndex, deletedProduct);
            } else {
                errors.add(new CoreError("Id error ", "wrong ID"));
            }
        }

        if (errors.isEmpty()) {
            return new DeleteProductFromCartResponse(request.getId());
        }
        return new DeleteProductFromCartResponse(errors);
    }

    private boolean isIdExist(long id) {
        return cartDatabase.isIdExist(id);
    }

    private Cart findProductById(long id) {
        return cartDatabase.findById(id);
    }
}