package team_static_startup.lesson_1;

public interface ProductDatabase {

    Long save(Product product);

    boolean delete(Long productId);

    boolean delete(Product product);

    void deleteByProductName(String product);

}
