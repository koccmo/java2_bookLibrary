package internet_store.core.service.cart;

import internet_store.core.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartProductsCountService {
    @Autowired
    private CartRepository CartRepository;

    public long getCartCount() {
        return CartRepository.countProductInCart();
    }
}