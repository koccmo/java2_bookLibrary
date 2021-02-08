package internet_store.core.service.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.domain.ProductInCart;
import internet_store.core.operation.Arithmetic;
import internet_store.core.persistence.CartRepository;
import internet_store.core.persistence.ProductRepository;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
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
    private ProductRepository productRepository;
    @Autowired
    private CartRepository CartRepository;
    @Autowired
    private Arithmetic arithmetic;

    public AddProductToCartResponse execute(AddProductToCartRequest request) {
        ProductInCart productInCart = new ProductInCart();
        List<CoreError> errors = new ArrayList<>();

        Product product = productRepository.findByTitle(request.getProductTitle());
        BigDecimal price = product.getPrice();

        productInCart.setProduct(product);
        productInCart.setQuantity(request.getNewQuantity());

        BigDecimal sum = arithmetic.multiplyBigDecimalAndRound(new BigDecimal(request.getNewQuantity().toString())
                , price, 2);
        productInCart.setSum(sum);

        CartRepository.save(productInCart);

        return new AddProductToCartResponse(productInCart.getSum());
    }
}