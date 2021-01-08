package internet_store.application.core.database;

import internet_store.application.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Profile("mysql")
public class MySQLDatabase implements Database{

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public MySQLDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(Product product) {
        String query = "INSERT INTO products (name, description, price) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setBigDecimal(3, product.getPrice());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean deleteByProductId(Long productId) {
        String query = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, productId);
            return preparedStatement;
        }) == 1;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public boolean deleteByProductName(String product) {
        return false;
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return null;
    }

    @Override
    public List<Product> findByProductDescription(String productDescription) {
        return null;
    }

    @Override
    public List<Product> findByNameAndDescription(String name, String description) {
        return null;
    }

    @Override
    public List<Product> getProductList() {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean changeProductName(Long id, String newName) {
        return false;
    }


}
