package lesson_2.service;

import internet_store.domain.Product;
import lesson_2.ProductListApplication;
import lesson_2.database.InnerDatabase;

import java.util.List;

public class UpdateProductService {
    private final InnerDatabase database;

    public UpdateProductService(InnerDatabase database) {
        this.database = database;
    }

    public void execute(long id, Product product) {
        List<Product> allProducts = database.getAllProducts();
        boolean isId = ProductListApplication.findProductService.isIdExist(allProducts, id);

        if (isId) {
            System.out.println("Product updated");
            int index = ProductListApplication.findProductService.findProductIndex(allProducts, id);
            database.updateProduct(index, product);
        } else {
            System.out.println("Can't find ID. Check and try again.");
        }
    }
}