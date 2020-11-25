package lesson_2.service;

import internet_store.core.domain.Product;
import lesson_2.database.InnerDatabase;

public class AddProductService {

    private final InnerDatabase database;

    public AddProductService(InnerDatabase database) {
        this.database = database;
    }

    public void execute(Product product) {
        database.addProduct(product);
    }
}