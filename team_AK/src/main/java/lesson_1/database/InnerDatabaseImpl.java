package lesson_1.database;

import internet_store.domain.Product;
import lesson_1.command_handler.AddProductCommand;
import lesson_1.product_find_handler.FindProduct;

import java.util.ArrayList;
import java.util.List;

public class InnerDatabaseImpl implements InnerDatabase {
    private final List<Product> products = new ArrayList<>();
    private final FindProduct findProduct;
    private Long id = 1L;

    public InnerDatabaseImpl(FindProduct findProduct) {
        this.findProduct = findProduct;
    }

    @Override
    public void add(Product product) {
        product.setId(id);
        products.add(product);
        id++;
    }

    @Override
    public void deleteById(Long id) {
        Product deletedProduct = findProduct.findById(products, id);

        if (deletedProduct != null) {
            products.remove(deletedProduct);
            System.out.println("Product removed");
        } else {
            System.out.println("Can't find ID. Check and try again.");
        }

    }

    @Override
    public void updateById(Long id) {
        Product updatedProduct = findProduct.findById(products, id);

        if (updatedProduct != null) {
            new AddProductCommand().addTo(updatedProduct);
            int productIndex = findProduct.findProductId(products, id);
            products.set(productIndex, updatedProduct);
            System.out.println("Product updated");
        } else {
            System.out.println("Can't find ID. Check and try again.");
        }
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

    private boolean isProductListEmpty(List<Product> products) {
        return products.size() == 0;
    }
}
