package internet_store.core.service.cart;

import internet_store.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartProductsCountService {
    @Autowired
    CartRepository cartRepository;

    public long getCartCount() {
        return cartRepository.countProductInCart();
    }
}