import java.util.List;

public interface ProductDataBase {

    List<Product> searchProductByName(String name);

    boolean addNewProduct(Product product);

    int removeProduct(String name);

    List<Product> getDatabase();

    int getDatabaseSize();

}
