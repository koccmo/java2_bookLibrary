package internet_store.persistence;

import internet_store.core.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT * FROM cart WHERE product_title=? AND deleted=false", nativeQuery = true)
    Cart findByTitle(String title);

    @Query(value = "SELECT COUNT(*) FROM cart WHERE deleted=false", nativeQuery = true)
    Long countProductInCart();

    @Query(value = "SELECT * FROM cart WHERE deleted=false limit ? offset ?", nativeQuery = true)
    List<Cart> getLimitsCartRecords(int limit, int offset);

    @Query(value = "SELECT SUM(sum) FROM cart WHERE deleted=false", nativeQuery = true)
    BigDecimal getCartTotalSum();
}