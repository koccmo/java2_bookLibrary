package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query(value = "INSERT INTO shopping_carts(customer_id) value customerId", nativeQuery = true)
    ShoppingCart add(Long customerId);

}
