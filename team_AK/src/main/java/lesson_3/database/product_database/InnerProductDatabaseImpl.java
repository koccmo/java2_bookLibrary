package lesson_3.database.product_database;


import lesson_3.core.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class InnerProductDatabaseImpl implements InnerProductDatabase {
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
        System.out.println("Products list:");
        products.forEach(pr -> System.out.println("ID: " + pr.getId() + " " +
                "Title: " + pr.getTitle() + " " + "Description: " + pr.getDescription() + " " +
                "Quantity: " + pr.getQuantity() + " " + "Price: " + pr.getPrice()));
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public void clear() {
        products.clear();
    }

    private boolean isProductListEmpty(List<Product> products) {
        return products.size() == 0;
    }


}
