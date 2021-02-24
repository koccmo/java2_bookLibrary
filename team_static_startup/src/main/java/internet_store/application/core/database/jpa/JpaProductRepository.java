package internet_store.application.core.database.jpa;

import internet_store.application.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, Long> {


}
