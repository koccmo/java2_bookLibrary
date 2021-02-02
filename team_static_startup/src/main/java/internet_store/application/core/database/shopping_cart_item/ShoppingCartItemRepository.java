package internet_store.application.core.database.shopping_cart_item;

import internet_store.application.core.domain.ProductShoppingCart;

import java.util.List;

public interface ShoppingCartItemRepository {

    Long add(Long shoppingCartId, Long productId, Long quantity);

    ProductShoppingCart findById(Long id);

    List<ProductShoppingCart> findAll();

}