package estore.database;

import estore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class JdbcProductDbImpl implements ProductDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> searchProductByName(String name) {
        return null;
    }

    @Override
    public List<Product> searchProductByCategory(String category) {
        return null;
    }

    @Override
    public boolean addNewProduct(Product product) {
        jdbcTemplate.update(
                "INSERT INTO products (prodName, prodDescription, category_id, quantity, price, dateOnStock) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getQuantity(),
                product.getPrice(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
        );
        return true;
    }

    @Override
    public int removeProductByName(String name) {
        return 0;
    }

    @Override
    public int removeProductById(Long id) {
        return 0;
    }

    @Override
    public List<Product> getDatabase() {
        return null;
    }

    @Override
    public int getDatabaseSize() {
        return 0;
    }
}
