package lesson_2.database;

import internet_store.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class InnerDatabaseImpl implements InnerDatabase {
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
    public void updateProduct(int index, Product product) {
        products.set(index, product);
    }

    @Override
    public void showReport() {
        if (isProductListEmpty(products)) {
            System.out.println("No records");
            return;
        }
        products.forEach(pr -> System.out.println("Product: " + "\n" + "ID: " + pr.getId() + "\n" +
                "Title: " + pr.getTitle() + "\n" + "Description: " + pr.getDescription() + "\n" +
                "Quantity: " + pr.getQuantity() + "\n" + "Price: " + pr.getPrice()));
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    private boolean isProductListEmpty(List<Product> products) {
        return products.size() == 0;
    }
}
