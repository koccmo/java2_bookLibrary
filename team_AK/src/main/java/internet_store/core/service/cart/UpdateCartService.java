package internet_store.core.service.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Cart;
import internet_store.core.domain.Product;
import internet_store.core.operation.Arithmetic;
import internet_store.core.request.cart.UpdateCartRequest;
import internet_store.core.response.cart.UpdateCartResponse;
import internet_store.core.validate.ProductQuantityValidator;
import internet_store.database.cart_database.CartDatabaseImpl;
import internet_store.database.product_database.ProductDatabaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UpdateCartService {
    @Autowired
    ProductDatabaseImpl productDatabase;
    @Autowired
    CartDatabaseImpl cartDatabase;
    @Autowired
    Arithmetic arithmetic;

    public UpdateCartResponse execute(UpdateCartRequest request) {
        ProductQuantityValidator quantityValidator = new ProductQuantityValidator();
        List<CoreError> errors = new ArrayList<>();
        int index = 0;
        Cart productInCart = new Cart();
        Long newQuantity = null;

        long productId = request.getId();
        boolean isProductIdExist = cartDatabase.isIdExist(productId);

        if (isProductIdExist) {
            productInCart = cartDatabase.findById(productId);
            index = cartDatabase.findProductIndex(productId);

            newQuantity = request.getNewQuantity();

            Product totalProductInDatabase = productDatabase.findByTitle(productInCart.getProduct().getTitle());

            errors = quantityValidator.validate(totalProductInDatabase.getQuantity(), newQuantity);

        } else {
            errors.add(new CoreError("Id error ", "Wrong Id"));
        }

        if (errors.isEmpty()) {
            productInCart.setQuantity(newQuantity);
            BigDecimal sum = arithmetic.multiplyBigDecimalAndRound(new BigDecimal(request.getNewQuantity().toString())
                    , productInCart.getProduct().getPrice(), 2);
            productInCart.setSum(sum);
            cartDatabase.updateCart(index, productInCart);
        }
        return new UpdateCartResponse(errors);
    }
}