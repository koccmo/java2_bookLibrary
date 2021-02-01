package internet_store.database.product_database;


import internet_store.core.domain.Product;
import internet_store.database.interfaces.ProductDatabase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Repository
public class ProductDatabaseImpl implements ProductDatabase {
    private final List<Product> products = new ArrayList<>();

    private Long id = 1L;

    @Override
    public void addProduct(Product product) {
        product.setId(id);
        products.add(product);
        id++;
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }

    @Override
    public void updateProduct(long index, Product product) {
        products.set((int) index, product);
    }

    @Override
    public void showReport() {
        products.forEach(pr -> System.out.println("ID: " + pr.getId() + " " +
                "Title: " + pr.getTitle() + " " + "Description: " + pr.getDescription() + " " +
                "Quantity: " + pr.getQuantity() + " " + "Price: " + pr.getPrice()));
    }

    @Override
    public boolean isIdExist(long id) {
        return products.stream().anyMatch(pr -> pr.getId() == id);
    }

    @Override
    public Product findById(long id) {
        return products.stream().filter(pr -> pr.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().orElse(null);
    }

    @Override
    public int findProductIndex(long id) {
        int LIST_FIRST_ELEMENT = 0;
        int NO_ID_FIND = -1;
        return IntStream.range(LIST_FIRST_ELEMENT, products.size())
                .filter(i -> products.get(i).getId() == id)
                .findFirst().orElse(NO_ID_FIND);
    }

    @Override
    public boolean isEmpty() {
        return products.size() == 0;
    }

    @Override
    public boolean existsByTitle(String title) {
        return false;
    }

    @Override
    public Long count() {
        return (long) products.size();
    }

    @Override
    public List<Product> getLimitsProductsRecords(int limit, int offset) {
        return null;
    }

    @Override
    public void addAll(List<Product> allProducts) {
        products.addAll(allProducts);
    }

    @Override
    public int recordsCount() {
        return products.size();
    }

    @Override
    public void clear() {
        products.clear();
    }

    @Override
    public void  setId(long id) {
        this.id=id;
    }
}