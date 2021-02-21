package lv.estore.app.core.database.products;

import lv.estore.app.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

//@Component
public class JdbcProductRepositoryImpl implements ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Long addProduct(Product product) {
        int result = jdbcTemplate.update(
                "INSERT INTO products (name, description, price) "
                        + "VALUES (?, ?, ?)",
                product.getName(), product.getDescription(), product.getPrice()
        );
        return (long) result;
    }

    @Override
    public boolean updateProductById(Long id, String name, String description, BigDecimal price) {
        int result = jdbcTemplate.update(
                "UPDATE products SET name = ?, description = ?, price = ? WHERE id = ?",
                name, description, price.toPlainString(), id
        );
        return result == 1;
    }

    @Override
    public boolean removeProductById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        Object[] args = new Object[] {id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean removeProductByName(String name) {
        String sql = "DELETE FROM products WHERE name = ?";
        Object[] args = new Object[] {name};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public Product findProductById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        Object[] args = new Object[] {id};
        List<Product> products = jdbcTemplate.query(sql, args, new ProductRowMapper());
        return products.stream().findFirst().orElse(null);
    }

    @Override
    public List<Product> findProductsByName(String name) {
        String sql = "SELECT * FROM products WHERE name = ?";
        Object[] args = new Object[] {name};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
    }

    @Override
    public List<Product> findProductsByPrice(BigDecimal price) {
        String sql = "SELECT * FROM products WHERE price = ?";
        Object[] args = new Object[] {price.toPlainString()};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
    }

    @Override
    public List<Product> findProductsByNameAndPrice(String name, BigDecimal price) {
        String sql = "SELECT * FROM products WHERE name = ? AND price = ? ";
        Object[] args = new Object[] {name, price.toPlainString()};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }
}
