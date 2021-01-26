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
        Object[] args = new Object[] { name };
        return jdbcTemplate.query(
                "SELECT * FROM products " +
                        "WHERE products.prodName = ?",
                args,
                new ProductRowMapper()
        );
    }

    @Override
    public List<Product> searchProductByCategory(String category) {
        Object[] args = new Object[] { category };
        return jdbcTemplate.query(
                "SELECT products.id, products.prodName, products.prodDescription, " +
                "products.category_Id, products.quantity, products.price " +
                " FROM products, productCategory " +
                " WHERE productCategory.category = ? " +
                "AND productCategory.id = products.category_Id",
                args,
                new ProductRowMapper()
        );
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
        jdbcTemplate.update(
                "DELETE FROM products " +
                        "WHERE products.prodName = ?",
                name
        );
        return 1;
    }

    @Override
    public int removeProductById(Long id) {
        jdbcTemplate.update(
                "DELETE FROM products " +
                        "WHERE products.id = ?",
                id
        );
        return 1;
    }

    @Override
    public List<Product> getDatabase() {
        return jdbcTemplate.query(
                "SELECT * FROM products",
                new ProductRowMapper()
        );
    }

    @Override
    public void updateProductPrice(Long id, Double price) {
        jdbcTemplate.update("UPDATE eStore_test.products " +
                "SET products.price = ? " +
                "WHERE products.id = ?",
                price,
                id);
    }

    @Override
    public int getDatabaseSize() {
        return 0;
    }
}
