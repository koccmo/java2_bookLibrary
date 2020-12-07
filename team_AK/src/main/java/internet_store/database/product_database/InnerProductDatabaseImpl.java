package internet_store.database.product_database;


import internet_store.core.domain.Product;
import dependency.annotation.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
@DIComponent
public class InnerProductDatabaseImpl implements InnerProductDatabase {
    private final List<Product> products = new ArrayList<>();
    private final Product EMPTY_OBJECT = null;

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
    public void updateProduct(int index, Product product) {
        products.set(index, product);
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
        return products.stream().filter(pr -> pr.getId() == id).findFirst().orElse(EMPTY_OBJECT);
    }

    @Override
    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().orElse(EMPTY_OBJECT);
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
}