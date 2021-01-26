package estore.database.jdbcrepo;

import estore.core.model.ProductCategory;
import estore.database.ProductCategoryRepository;
import estore.database.ProductCategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcProductCategoryRepositoryImpl implements ProductCategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductCategory> getDatabase() {
        return jdbcTemplate.query(
                "SELECT * FROM productCategory",
                new ProductCategoryRowMapper()
        );
    }

    @Override
    public boolean addNewCategory(ProductCategory category) {
        jdbcTemplate.update(
                "INSERT INTO productCategory (category) " +
                        "VALUES (?)",
                category.getCategory());
        return true;
    }
}
