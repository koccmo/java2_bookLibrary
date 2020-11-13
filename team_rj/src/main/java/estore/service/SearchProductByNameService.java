package estore.service;

import estore.database.ProductDataBase;
import estore.domain.Product;

import java.util.List;

public class SearchProductByNameService {

    private ProductDataBase database;

    public SearchProductByNameService(ProductDataBase database) {
        this.database = database;
    }

    public List<Product> execute(String productToSearch) {
        List<Product> foundProducts = database.searchProductByName(productToSearch);
        return foundProducts;
    }

}
