package internet_store.database.product;

import internet_store.core.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component
public class JDBCProductDatabaseImpl implements ProductDatabase{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getProducts() {
        String sql = "SELECT* FROM products";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public void add(Product product) {
        jdbcTemplate.update("INSERT INTO products(title, description, price)"
                        + "VALUES (?, ?, ?)",
                product.getTitle(), product.getDescription(), product.getPrice());
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean deleteAllByTitle(String title) {
        String sql = "DELETE FROM products WHERE title = ?";
        Object[] args = new Object[]{title};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean deleteAllByDescription(String description) {
        String sql = "DELETE FROM products WHERE description = ?";
        Object[] args = new Object[]{description};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean deleteAllByPriceRange(Integer startPrice, Integer endPrice) {
        String sql = " DELETE FROM products WHERE price >= :startPrice and price <= :endPrice";
        Object[] args = new Object[]{startPrice, endPrice};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean deleteAllByTitleAndDescription(String title, String description){
        String sql = "DELETE FROM products WHERE title = ? " +
                     "and WHERE description = ?";
        Object[] args = new Object[]{title, description};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean deleteAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice){
        String sql = "DELETE FROM products WHERE description = ? " +
                "and WHERE price >= :startPrice and price <= :endPrice";
        Object[] args = new Object[]{description, startPrice, endPrice};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean deleteAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice){
        String sql = "DELETE FROM products WHERE title = ? " +
                "and WHERE price >= :startPrice and price <= :endPrice";
        Object[] args = new Object[]{title, startPrice, endPrice};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public boolean deleteAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice){
        String sql = "DELETE FROM products WHERE title = ? " +
                "and WHERE description = ? and WHERE price >= :startPrice and price <= :endPrice";
        Object[] args = new Object[]{title, startPrice, endPrice};
        return jdbcTemplate.update(sql, args) == 1;
    }

    @Override
    public Optional<Product> searchById(Long id) {
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
    public List<Product> searchAllByTitle(String title) {
        String sql = "SELECT * FROM products WHERE title = ?";
        Object[] args = new Object[]{title};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
    }

    @Override
    public List<Product> searchAllByPriceRange(Integer startPrice, Integer endPrice) {
        String sql = "SELECT * FROM products WHERE price >= :startPrice and price <= :endPrice ";
        Object[] args = new Object[] {startPrice,endPrice};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
    }

    @Override
    public List<Product> searchAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice) {
        String sql = "SELECT FROM products WHERE title = ? " +
                "and WHERE description = ? and WHERE price >= :startPrice and price <= :endPrice";
        Object[] args = new Object[]{title, startPrice, endPrice};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
    }

    @Override
    public List<Product> searchAllByDescription(String description) {
        String sql = "SELECT * FROM products WHERE description = ?";
        Object[] args = new Object[]{description};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
    }

    @Override
    public List<Product> searchAllByTitleAndDescription(String title, String description) {
        String sql = "SELECT * FROM products WHERE title = ? AND description = ?";
        Object[] args = new Object[]{title, description};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
    }

    @Override
    public List<Product> searchAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice) {
        String sql = "SELECT FROM products WHERE title = ? " +
                "and WHERE price >= :startPrice and price <= :endPrice";
        Object[] args = new Object[]{title, startPrice, endPrice};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
    }

    @Override
    public List<Product> searchAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice) {
        String sql = "SELECT FROM products WHERE description = ? " +
                "and WHERE price >= :startPrice and price <= :endPrice";
        Object[] args = new Object[]{description, startPrice, endPrice};
        return jdbcTemplate.query(sql, args, new ProductRowMapper());
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

    @Override
    public boolean containsPrice(Integer price) {
        return false;
    }

    @Override
    public boolean containsTitleAndDescription(String title, String description) {
        return false;
    }
}
