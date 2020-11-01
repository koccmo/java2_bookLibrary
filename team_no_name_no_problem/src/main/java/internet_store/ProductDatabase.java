package internet_store;

public interface ProductDatabase {

    boolean save(Product product);

    boolean deleteById(long id);

    boolean printProducts();

    boolean changeTitle(long id, String newTitle);

    boolean changeDescription(long id, String newDescription);
}
