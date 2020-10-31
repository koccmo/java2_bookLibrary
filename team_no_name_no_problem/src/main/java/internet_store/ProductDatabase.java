package internet_store;

public interface ProductDatabase {

    boolean save(Product product);

    boolean deleteById(long id);


    void printProducts();
}
