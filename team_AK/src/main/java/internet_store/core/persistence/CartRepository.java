package internet_store.core.persistence;

import internet_store.core.domain.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<ProductInCart, Long> {
    @Query(value = "SELECT * FROM cart WHERE product_title=? AND deleted=false", nativeQuery = true)
    ProductInCart findByTitle(String title);

    @Query(value = "SELECT COUNT(*) FROM cart WHERE deleted=false AND ordered=false", nativeQuery = true)
    Long countProductInCart();

    @Query(value = "SELECT * FROM cart WHERE deleted=false AND ordered=false limit ? offset ?", nativeQuery = true)
    List<ProductInCart> getLimitsCartRecords(int limit, int offset);

    @Query(value = "SELECT SUM(sum) FROM cart WHERE deleted=false and ordered=false", nativeQuery = true)
    BigDecimal getCartTotalSum();

    @Query(value = "SELECT * FROM cart WHERE deleted=false AND ordered=false", nativeQuery = true)
    List<ProductInCart> itemsForOrder();
}