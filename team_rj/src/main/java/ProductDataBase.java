import java.util.List;

public interface ProductDataBase {

    List<Product> searchProductByName(String name);

    boolean addNewProduct(Product product);

    int removeProduct(String name);

    boolean printProducts();

    List<Product> getDatabase();

    int getDatabaseSize();

}
