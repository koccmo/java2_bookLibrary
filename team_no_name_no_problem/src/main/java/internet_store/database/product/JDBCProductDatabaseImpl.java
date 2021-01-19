package internet_store.database.product;

import internet_store.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JDBCProductDatabaseImpl implements ProductDatabase{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public void add(Product product) {
        jdbcTemplate.update("INSERT INTO products(title, description, price)"
                        + "VALUES (?, ?, ?)",
                product.getTitle(), product.getDescription(), product.getPrice());
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAllByTitle(String title) {

    }

    @Override
    public void deleteAllByDescription(String description) {

    }

    @Override
    public void deleteAllByPriceRange(Integer startPrice, Integer endPrice) {

    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void changeTitle(Long id, String newTitle) {

    }

    @Override
    public void changeDescription(Long id, String newDescription) {

    }

    @Override
    public void changePrice(Long id, Integer newPrice) {

    }

    @Override
    public List<Product> findAllByTitle(String title) {
        return null;
    }

    @Override
    public List<Product> findAllByPriceRange(Integer startPrice, Integer endPrice) {
        return null;
    }

    @Override
    public List<Product> findAllByDescription(String description) {
        return null;
    }

    @Override
    public List<Product> findAllByTitleAndDescription(String title, String description) {
        return null;
    }

    @Override
    public boolean containsProduct(Product product) {
        return false;
    }

    @Override
    public boolean containsId(Long id) {
        return false;
    }

    @Override
    public boolean containsTitle(String title) {
        return false;
    }

    @Override
    public boolean containsDescription(String description) {
        return false;
    }
}
