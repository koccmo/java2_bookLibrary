package internet_store.application.core.database.jpa;

import internet_store.application.core.dto.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {


}
