package lesson_2.service;

import internet_store.domain.Product;
import lesson_2.ProductListApplication;
import lesson_2.database.InnerDatabase;

import java.util.List;

public class RemoveProductService {
    private final InnerDatabase database;

    public RemoveProductService(InnerDatabase database) {
        this.database = database;
    }

    public void execute(long id) {
        List<Product> allProducts = database.getAllProducts();
        Product deletedProduct = ProductListApplication.findProductService.findById(allProducts, id);

        if (deletedProduct != null) {
            database.deleteProduct(deletedProduct);
            System.out.println("Product removed");
        } else {
            System.out.println("Can't find ID. Check and try again.");
        }
    }
}
