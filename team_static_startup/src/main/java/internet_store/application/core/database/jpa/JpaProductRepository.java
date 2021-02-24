package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long> {

    @Query("UPDATE Product p SET p.productName = :name WHERE p.id =:id")
    Long changeProductName(@Param("id") Long id, @Param("name") String newName);

    @Query("DELETE FROM Product WHERE id = :id")
    Long deleteByProductId(Long id);

    Long deleteByName(String name);

    Long deleteByNameAndDescriptionAndPrice(String name, String description, BigDecimal price);

    List<Product> findProductByName(String name);

    List<Product> findProductByDescription(String description);

    List<Product> findProductByNameAndDescription (String name, String description);

}
