package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {


}
