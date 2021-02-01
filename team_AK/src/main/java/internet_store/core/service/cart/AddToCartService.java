package internet_store.core.service.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Cart;
import internet_store.core.domain.Product;
import internet_store.core.operation.Arithmetic;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.validate.ProductQuantityValidator;
import internet_store.database.cart_database.CartDatabaseImpl;
import internet_store.database.product_database.ProductDatabaseImpl;
import internet_store.persistence.CartRepository;
import internet_store.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AddToCartService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDatabaseImpl productDatabase;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartDatabaseImpl cartDatabase;
    @Autowired
    Arithmetic arithmetic;

    public AddProductToCartResponse execute(AddProductToCartRequest request) {
        Cart cart = new Cart();
        ProductQuantityValidator quantityValidator = new ProductQuantityValidator();
        List<CoreError> errors = new ArrayList<>();

        if (request.getDatabase() instanceof CartRepository) {
            Product product = productRepository.findByTitle(request.getProductTitle());
            BigDecimal price = product.getPrice();

            cart.setTitle(product.getTitle());
            cart.setPrice(price);
            cart.setQuantity(request.getNewQuantity());

            BigDecimal sum = arithmetic.multiplyBigDecimalAndRound(new BigDecimal(request.getNewQuantity().toString())
                    , price, 2);
            cart.setSum(sum);

            cartRepository.save(cart);
        }

        if (request.getDatabase() instanceof CartDatabaseImpl) {
            long productId = request.getId();

            boolean isProductIdExist = productDatabase.isIdExist(productId);
            Product findProductAddToCart = new Product();
            if (isProductIdExist) {
                findProductAddToCart = productDatabase.findById(productId);
                Long quantityToCart = request.getNewQuantity();
                errors = quantityValidator.validate(findProductAddToCart.getQuantity(), quantityToCart);
            } else {
                errors.add(new CoreError("Id error ", "Wrong Id"));
            }
            if (errors.isEmpty()) {
                cart.setTitle(findProductAddToCart.getTitle());
                cart.setPrice(findProductAddToCart.getPrice());
                cart.setQuantity(request.getNewQuantity());
                BigDecimal sum = arithmetic.multiplyBigDecimalAndRound(new BigDecimal(request.getNewQuantity().toString())
                        , findProductAddToCart.getPrice(), 2);
                cart.setSum(sum);
                cartDatabase.addProductToCart(cart);
            }
        }
        return new AddProductToCartResponse(errors);
    }
}