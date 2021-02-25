package internet_store.application.core.database.jpa;

import internet_store.application.core.dto.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    @Query(value = "INSERT INTO shopping_cart_items(" +
            "shopping_cart_id, product_id, quantity) " +
            "value shoppingCartId, productId, quantity", nativeQuery = true)
    Long save(Long shoppingCartId, Long productId, Long quantity);

}
